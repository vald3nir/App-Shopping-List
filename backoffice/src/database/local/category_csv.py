from toolkit.utils import file_csv_to_json
from toolkit.utils import json_to_file_csv

from src.datasets import CATEGORIES_PATH
from src.domain.dto.category_dto import CategoryDTO


class CategoryCSV:

    def __init__(self):
        self.file_csv = CATEGORIES_PATH

    def load_categories(self) -> list[dict]:
        data: list[dict] = file_csv_to_json(file_csv=self.file_csv)
        return [CategoryDTO.model_validate(item).model_dump() for item in data]

    def save_categories(self, categories: list[dict]):
        json_to_file_csv(file_csv=self.file_csv, data_json=categories)
