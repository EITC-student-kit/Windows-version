__author__ = 'kris'

from datetime import datetime


class Notification():
    """A notification abstraction"""

    def __init__(self, name, year, month, day, hour, minute):
        """The created instance has a name and a datetime.

        :param name: A notification name
        :type name: str
        :param year: year of the notification
        :type year: int
        :param month: month of the notification
        :type month: int
        :param day: day of notification
        :type day: int
        :param hour: hour of notification
        :type hour: int
        :param minute: minute of notification
        :type minute: int
        """
        self._name = name
        self._datetime = datetime(year, month, day, hour, minute)

    def get_name(self):
        return self._name

    def get_datetime(self):
        return self._datetime

    def is_due(self):
        return self._datetime <= datetime.now()

if __name__ == "__main__":
    pass