from toolkit.utils import file_csv_to_json
from toolkit.utils import json_to_file_csv

from src.datasets import PRODUCTS_PATH
from src.domain.dto.product_dto import ProductDTO


class ProductCSV:

    def __init__(self):
        self.file_csv = PRODUCTS_PATH

    def load_products(self) -> list[dict]:
        data: list[dict] = file_csv_to_json(file_csv=self.file_csv)
        return [ProductDTO.model_validate(item).model_dump() for item in data]

    def save_products(self, products: list[dict]):
        json_to_file_csv(file_csv=self.file_csv, data_json=products)
