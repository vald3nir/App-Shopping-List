# 🛒 Lista de Compras - Android

<img width="1024" height="500" alt="Gemini_Generated_Image_x7dixsx7dixsx7di" src="https://github.com/user-attachments/assets/b1f5b58e-73d5-43fe-bf4e-8e341c5f0395" />

Aplicativo Android para gerenciar listas de compras de forma rápida, prática e organizada.

## ✨ Funcionalidades

- Criar, editar e remover listas de compras
- Adicionar, editar e excluir itens das listas
- Marcar itens como comprados
- Ler código de barras para buscar produtos
- Buscar dados de produtos pela API pública
- Autenticação de usuário e tela de perfil
- Sincronização de listas e produtos
- Persistência local para uso offline
- Suporte a temas

## 🧰 Tecnologias e bibliotecas

- Kotlin
- Jetpack Compose
- Material Design 3
- ViewModel
- StateFlow / Flow
- Room
- Hilt
- Coroutines
- Firebase
- Crashlytics
- Firebase Performance
- Google Services

## 🏗 Arquitetura

O app segue uma arquitetura baseada em **MVVM** e princípios de **Clean Code**:

- **Presentation**: telas Compose e `ViewModel`
- **Domain**: DTOs, mappers, providers e regras de negócio
- **Repository/Data**: acesso a banco local, APIs e serviços remotos
- **Core**: módulos de apoio como analytics e sincronização
- **Toolkit**: bibliotecas compartilhadas entre módulos

## 📁 Estrutura de pastas e arquivos

```text
AppShoppingList/
├─ app/
│  ├─ build.gradle.kts
│  ├─ google-services.json
│  ├─ proguard-rules.pro
│  └─ src/
│     ├─ main/
│     │  ├─ AndroidManifest.xml
│     │  ├─ java/com/vald3nir/shoppinglist/
│     │  │  ├─ core/{analytics,sync}/
│     │  │  ├─ domain/{dto,enums,mapper,providers}/
│     │  │  ├─ presentation/{components,features,main}/
│     │  │  └─ repository/{api,database,di,impls,usecases}/
│     │  ├─ res/
│     │  │  ├─ drawable/
│     │  │  ├─ mipmap-*/
│     │  │  └─ values/
│     │  ├─ assets/
│     │  ├─ dev/res/values/
│     │  └─ prod/res/values/
│     └─ ...
├─ toolkit/
│  ├─ build-logic/
│  ├─ core/
│  ├─ designsystem/
│  └─ libs/
│     ├─ auth/
│     ├─ camera/
│     └─ servicelocation/
├─ gradle/
│  └─ libs.versions.toml
├─ build.gradle.kts
├─ settings.gradle.kts
├─ gradle.properties
├─ gradlew
├─ gradlew.bat
└─ README.md
```

## 📲 Baixe na Play Store

https://play.google.com/store/apps/details?id=com.vald3nir.shoppinglist
