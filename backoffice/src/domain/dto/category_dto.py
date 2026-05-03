from pydantic import BaseModel


class CategoryDTO(BaseModel):
    id: str
    name: str
    created_at: str
    updated_at: str
    icon_url: str
