__author__ = "Kristo Koert"

import gtk
import appindicator
from timeManager import Stopper
from threading import ThreadError
from notificationTracking import NotificationHandler

gtk.gdk.threads_init()


class ToolbarGUI():
    """Linux gtk toolbar application indicator with a menu containing three elements and a sub-menu attached to the first
     element of the main menu containing another 6 elements.
    """

    def __init__(self):

        self.indicator = appindicator.Indicator("app-doc-menu", "applications-haskell",
                                                appindicator.CATEGORY_APPLICATION_STATUS)

        self.indicator.set_status(appindicator.STATUS_ACTIVE)
        self.indicator.set_attention_icon("indicator-messages-new")

        self.main_menu = gtk.Menu()
        self.sub_menu = gtk.Menu()

        self.main_menu_items = [gtk.ImageMenuItem("Tracking.."),
                                gtk.MenuItem("More.."),
                                gtk.ImageMenuItem("Notification!")]

        self.sub_menu_items = [gtk.ImageMenuItem("Productive"),
                               gtk.ImageMenuItem("Neutral"),
                               gtk.ImageMenuItem("Counter-Productive"),
                               gtk.MenuItem("Stop"),
                               gtk.MenuItem("Reset"),
                               gtk.MenuItem("Undo")]

        self.notification_button = self.main_menu_items[2]
        self.productive_button = self.sub_menu_items[0]
        self.neutral_button = self.sub_menu_items[1]
        self.counter_productive_button = self.sub_menu_items[2]
        self.stop_button = self.sub_menu_items[3]
        self.reset_button = self.sub_menu_items[4]
        self.undo_button = self.sub_menu_items[5]

        self.sub_menu_items[0].set_always_show_image(True)
        self.sub_menu_items[1].set_always_show_image(True)
        self.sub_menu_items[2].set_always_show_image(True)

        for item in self.main_menu_items:
            self.main_menu.append(item)
            item.show()

        for item in self.sub_menu_items:
            self.sub_menu.append(item)

        self.main_menu_items[0].set_submenu(self.sub_menu)

        self.main_menu_items[1].connect("activate", self.on_more_clicked)
        self.notification_button.connect("activate", self.notification_checked)
        self.productive_button.connect("activate", self.on_productivity_choice_clicked)
        self.neutral_button.connect("activate", self.on_productivity_choice_clicked)
        self.counter_productive_button.connect("activate", self.on_productivity_choice_clicked)
        self.stop_button.connect("activate", self.on_stop_clicked)
        self.reset_button.connect("activate", self.on_reset_clicked)

        self.set_sub_menu_state_tracking(False)

        self.indicator.set_menu(self.main_menu)

        self._stopper = None
        self._notification_handler = NotificationHandler(self.indicator, self.notification_button)
        self._notification_handler.start()
        self.notification_button.hide()

    def on_productivity_choice_clicked(self, widget):
        """Event handler for all three productivity type choices. Independent of choice sets sub-menu state to
        tracking.

        :param widget: the widget that triggered this event handler
        :type widget: gtk.ImageMenuItem
        """
        self.set_sub_menu_state_tracking(True)
        self.start_stopper(self.indicator)

    def on_stop_clicked(self, widget):
        """Event handler for stopping the timer.

        :param widget: the widget that triggered this event handler
        :type widget: gtk.MenuItem
        """
        if widget.get_label() == "Stop":
            widget.set_label("Start")
        else:
            widget.set_label("Stop")
        self._stopper.toggle_active()

    def on_reset_clicked(self, widget):
        """Event handler for resetting the stopper.

        :param widget: the widget that triggered this event handler
        :type widget: gtk.MenuItem
        """
        self.stop_button.set_label("Stop")
        self.set_sub_menu_state_tracking(False)
        widget.hide()
        self.reset_stopper()

    def start_stopper(self, widget):
        """Leaves the gtk thread, creates a Stopper object there that is referenced in this object and starts it.

        :param widget: the widget that triggered this event handler
        :type widget: gtk.MenuItem
        """
        try:
            gtk.threads_leave()
            self._stopper = Stopper(self.indicator)
            self._stopper.toggle_active()
            self._stopper.start()
        except ThreadError:
            print "Threading problem in toolbarGUI."
        finally:
            gtk.threads_enter()

    def reset_stopper(self):
        """Deals with resetting the Stopper object via stopping it and removing the reference to it. The indicators
        label is also reset to empty.
        """
        self._stopper.stop_tracking()
        self._stopper = None
        self.indicator.set_label("")

    def on_more_clicked(self, widget):
        raise NotImplementedError

    def notification_checked(self, widget):
        """Removes notification signs.

        :param widget: the widget that triggered this event handler
        :type widget: gtk.MenuItem
        """
        self._notification_handler.remove_notification()

    def set_sub_menu_state_tracking(self, is_true=True):
        """Sets the sub-menu to the appropriate state dependent on whether or not activity tracking is going on
        or not.(This is expressed via the is_true variable.

        :param is_true: is an activity type being tracked
        :type is_true: bool
        """
        try:
            assert isinstance(is_true, bool)
        except AssertionError:
            print "is_true must be boolean value"
        finally:
            if is_true:
                self.productive_button.hide()
                self.neutral_button.hide()
                self.counter_productive_button.hide()
                self.reset_button.show()
                self.stop_button.show()
            elif not is_true:
                self.productive_button.show()
                self.neutral_button.show()
                self.counter_productive_button.show()
                self.reset_button.hide()
                self.stop_button.hide()

if __name__ == "__main__":
    gui = ToolbarGUI()
    gtk.threads_enter()
    gtk.main()
    gtk.threads_leave()