from src.database.firebase.category_dao import CategoryDAO
from src.database.firebase.product_dao import ProductDAO
from src.database.firebase.version_tables_dao import VersionTablesDAO
from src.database.local.category_csv import CategoryCSV
from src.database.local.product_csv import ProductCSV
from src.database.local.version_tables_json import VersionTablesJson


# --------------------------------------------------------------------------------------------------------------

def restore_version_tables():
    version_tables = VersionTablesJson().load_version_tables()
    VersionTablesDAO().save_version_tables(version_tables)


def restore_products():
    products: list[dict] = ProductCSV().load_products()
    ProductDAO().save_products(products=products)


def restore_category():
    categories: list[dict] = CategoryCSV().load_categories()
    CategoryDAO().save_categories(categories=categories)


# --------------------------------------------------------------------------------------------------------------

def restore_database():
    restore_version_tables()
    restore_products()
    restore_category()
