__author__ = "Kristo Koert"

from threading import Thread
from time import sleep


class Stopper(Thread):
    """A thread object that is meant to replicate a stopper. Has the ability to stop temporarily and continue. Though
    a new instance should be created for tracking another activity. This is due to performance considerations.
    """

    _show_time_in_indicator = True

    def __init__(self, indicator):
        """Initialization uses the super class Thread __init__ function and sets a gtk.PictureMenuItem object to
        be used as a display point for the running stopper time.

        :param indicator: A place to display stopper time
        :type indicator:
        """
        super(Stopper, self).__init__()
        self._indicator = indicator
        self._time = 0
        self._active = False
        self._exit_thread = False

    def run(self):
        """As long as _exit_thread is false, this objects instance will stay active. When it's true, the instance will
        become inactive and should be left for garbage collection by removing all references to it. Toggling _active
        creates a stop and resume effect."""
        while not self._exit_thread:
            while self._active and not self._exit_thread:
                if self._show_time_in_indicator:
                    self._indicator.set_label(self._seconds_to_min(self._time))
                sleep(1)
                self._time += 1
            while not self._active and not self._exit_thread:
                sleep(1)

    def toggle_active(self):
        """Toggles stopper active."""
        self._active = not self._active

    def stop_tracking(self):
        """Lets this thread instance finish. After this all references to this instance should be removed."""
        self._exit_thread = True

    def toggle_show_time_in_indicator(self):
        """Toggle whether the running time should be shown in the appindicator."""
        self._show_time_in_indicator = not self._show_time_in_indicator

    @staticmethod
    def _seconds_to_min(s):
        """Turns seconds input into a time format

        :param s: seconds
        :type s: int
        :returns: formatted string
        """
        h = m = 0
        while s > 60:
            s -= 60
            m += 1
        while m > 60:
            m -= 60
            h += 1
        return "{0:02}:{1:02}:{2:02}".format(h, m, s)


if __name__ == "__main__":
    pass