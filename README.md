# 💵 Dollar Room App

Aplicación Android desarrollada en Kotlin que permite registrar transacciones de compra y venta de dólares, almacenando la información localmente mediante Room Database.

---

## 📱 Descripción

Esta aplicación permite al usuario:

- Registrar compras de dólares (BUY)
- Registrar ventas de dólares (SELL)
- Visualizar el historial de transacciones
- Calcular automáticamente el saldo disponible

Toda la información se almacena localmente usando Room, siguiendo arquitectura MVVM.

---

## 🧩 Arquitectura

La aplicación está estructurada utilizando el patrón **MVVM**:


  - **View (UI)**  

    - `FirstFragment`

    - `MainActivity`

    - `TransactionAdapter`


- **ViewModel**  

     - `TransactionViewModel`

- **Model**  

    - `DollarTrasanction` (Entity)

    - `TransactionRepository`

    - `DollarTransactionDao`
    
    - `AppDatabase`

---

## 🛠️ Tecnologías utilizadas

- Kotlin
- Android SDK
- Room Database
- RecyclerView
- ViewModel + LiveData
- Coroutines

---

## 🗂️ Estructura del proyecto

```

com.example.dollar_room_pm
│
├── Model
│   ├── Local
│   │   ├── AppDatabase.kt
│   │   ├── DollarTransactionDao.kt
│   │   └── DollarTrasanction.kt
│   └── TransactionRepository.kt
│
├── View
│   ├── FirstFragment.kt
│   ├── MainActivity.kt
│   └── TransactionAdapter.kt
│
├── ViewModel
│   └── TransactionViewModel.kt

````

---

## ⚙️ Instalación

### 🔹 Opción 1: Clonar repositorio

```bash
git clone https://github.com/TU-USUARIO/TU-REPO.git
````

Luego abrir en **Android Studio**.

---

### 🔹 Opción 2: Descargar ZIP

1. Ir al repositorio en GitHub
2. Click en **Code → Download ZIP**
3. Descomprimir
4. Abrir en Android Studio

---

## ▶️ Ejecución

1. Abrir el proyecto en Android Studio
2. Esperar que Gradle sincronice
3. Ejecutar en emulador o dispositivo físico

---

## 📊 Funcionalidad

* Ingresar monto
* Presionar:

  * **BUY → suma al saldo**
  * **SELL → resta del saldo (si hay fondos disponibles)**
* Visualizar historial en lista

---

## ⚠️ Validaciones

* No permite vender más de lo disponible
* Muestra mensajes de error si el saldo es insuficiente

---

## 🚀 Mejoras futuras

- 🧹 Eliminar historial de transacciones (reset de datos)
- 📊 Visualización de datos mediante gráficos (ej: compras vs ventas)
- 📈 Indicadores de saldo acumulado
- 🎨 Mejoras UI/UX adicionales
- 🔍 Filtros por tipo de transacción (BUY / SELL)

---

## 👩‍💻 Autor

DP
