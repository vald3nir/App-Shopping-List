from toolkit.utils import read_json
from toolkit.utils import write_json

from src.datasets import VERSION_TABLES_PATH


class VersionTablesJson:

    def __init__(self):
        self.file_json = VERSION_TABLES_PATH

    def load_version_tables(self) -> dict:
        return read_json(file_path=self.file_json)

    def save_version_tables(self, version_tables: dict):
        write_json(file_path=self.file_json, data=version_tables)
