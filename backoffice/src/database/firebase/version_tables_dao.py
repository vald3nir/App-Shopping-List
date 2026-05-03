from src.database.firebase import firebase_singleton_dao
from src.domain.dto.version_tables_dto import VersionTablesDTO


class VersionTablesDAO:

    def __init__(self):
        self.db = firebase_singleton_dao

    def load_version_tables(self) -> dict:
        item = self.db.load(path="version_tables")
        return VersionTablesDTO.model_validate(item).model_dump()

    def save_version_tables(self, version_tables: dict):
        self.db.insert_or_update(path="version_tables", data=version_tables)
