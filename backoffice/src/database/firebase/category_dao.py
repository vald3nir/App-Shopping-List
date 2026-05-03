from src.database.firebase import firebase_singleton_dao
from src.domain.dto.category_dto import CategoryDTO


class CategoryDAO:

    def __init__(self):
        self.db = firebase_singleton_dao

    def load_categories(self) -> list[dict]:
        data = self.db.load(path="categories")
        return [CategoryDTO.model_validate(item).model_dump() for item in data]

    def save_categories(self, categories: list[dict]):
        self.db.insert_or_update(path="categories", data=categories)
