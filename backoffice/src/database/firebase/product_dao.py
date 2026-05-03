from src.database.firebase import firebase_singleton_dao
from src.domain.dto.product_dto import ProductDTO


class ProductDAO:

    def __init__(self):
        self.db = firebase_singleton_dao

    def load_products(self) -> list[dict]:
        data = self.db.load(path="products")
        return [ProductDTO.model_validate(item).model_dump() for item in data]

    def save_products(self, products: list[dict]):
        self.db.insert_or_update(path="products", data=products)
