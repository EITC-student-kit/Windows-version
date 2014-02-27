__author__ = "Kristo Koert"

import gtk
import appindicator

from timeManager import Stopper


gtk.gdk.threads_init()


class ToolbarGUI():
    _stopper = None

    def __init__(self):
        self.indicator = appindicator.Indicator("debian-doc-menu", "applications-python",
                                                appindicator.CATEGORY_APPLICATION_STATUS)

        self.indicator.set_status(appindicator.STATUS_ACTIVE)
        self.indicator.set_attention_icon("indicator-messages-new")

        self.menu = gtk.Menu()

        self.menu_items = [gtk.MenuItem("Start Tracking"), gtk.MenuItem("Cancel Tracking"), gtk.MenuItem("More..")]
        for item in self.menu_items:
            self.menu.append(item)

        self.menu_items[0].connect("activate", self.on_toggle_stopper)
        self.menu_items[1].connect("activate", self.on_cancel_clicked)
        self.menu_items[2].connect("activate", self.on_more_clicked)

        self.menu_items[0].show()
        self.menu_items[2].show()

        self.indicator.set_menu(self.menu)

    def start_stopper(self):
        if self._stopper is None:
            gtk.threads_leave()
            self._stopper = Stopper(self.indicator)
            gtk.threads_enter()
            self._stopper.toggle_active()
            self._stopper.start()
        else:
            self._stopper.toggle_active()

    def stop_stopper(self):
        self._stopper.toggle_active()

    def reset_stopper(self):
        self._stopper.stop()
        self._stopper = None
        self.indicator.set_label("")

    def on_toggle_stopper(self, button):
        if button.get_label() == "Start Tracking":
            button.set_label("Stop Tracking")
            self.menu_items[1].show()
            self.start_stopper()
        else:
            self.stop_stopper()
            button.set_label("Start Tracking")

    def on_more_clicked(self, w):
        pass

    def on_cancel_clicked(self, button):
        button.hide()
        self.menu_items[0].set_label("Start Tracking")
        self.reset_stopper()


if __name__ == "__main__":
    gui = ToolbarGUI()
    gtk.threads_enter()
    gtk.main()
    gtk.threads_leave()



