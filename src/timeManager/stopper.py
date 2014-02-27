__author__ = "Kristo Koert"

from threading import Thread
from time import sleep


class Stopper(Thread):
    _indicator = None
    _time = 0
    _active = False
    _exit_thread = False

    def __init__(self, indicator):
        super(Stopper, self).__init__()
        self._indicator = indicator

    def run(self):
        while self._active and not self._exit_thread:
            self._indicator.set_label(self._seconds_to_min(self._time))
            sleep(1)
            self._time += 1
        while not self._active and not self._exit_thread:
            sleep(1)

    def toggle_active(self):
        self._active = not self._active

    def is_active(self):
        return self._active

    def stop(self):
        self._exit_thread = True

    @staticmethod
    def _seconds_to_min(s):
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