import os

from dotenv import load_dotenv

env_path = f"{os.path.relpath(os.path.dirname(__file__))}{os.sep}.env"
load_dotenv(dotenv_path=env_path)

from src.domain.use_cases.backup_use_case import backup_category
from src.domain.use_cases.restore_use_case import restore_database

if __name__ == '__main__':
    backup_category()
    restore_database()
    print("finish")
