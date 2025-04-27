# GastosMóvil (Gastos y Presupuesto)

**GastosMóvil** es una aplicación **Android** desarrollada en **Kotlin** con **Jetpack Compose** que permite llevar el registro de gastos e ingresos personales. Este proyecto corresponde al parcial de la asignatura y su objetivo principal ha sido implementar la capa de **autenticación** con **Firebase Authentication (Email/Password)** y preparar la base para la **persistencia en Firestore**.

---

## 📋 Tabla de Contenidos

1. [Características](#características)
2. [Tecnologías y Librerías](#tecnologías-y-librerías)
3. [Estructura del Proyecto](#estructura-del-proyecto)
4. [Requisitos Previos](#requisitos-previos)
5. [Instalación y Configuración](#instalación-y-configuración)
6. [Flujo de Autenticación](#flujo-de-autenticación)
7. [Pantallas y Navegación](#pantallas-y-navegación)
8. [Arquitectura](#arquitectura)
9. [Ejecución](#ejecución)
10. [Licencia](#licencia)

---

## ⭐ Características

- Registro e inicio de sesión de usuarios con correo y contraseña.
- Prevención de acceso a pantallas protegidas.
- Estructura base para almacenar transacciones en **Firestore**.
- Interfaz construida con **Jetpack Compose**.
- Navegación mediante **Navigation Component**.

---

## 🛠 Tecnologías y Librerías

- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose
- **Arquitectura:** MVVM
- **Autenticación y BD:** Firebase Authentication & Firestore
- **Navegación:** AndroidX Navigation Compose
- **ViewModel:** AndroidX Lifecycle ViewModel
- **Inyección sencilla:** ViewModelProvider

---

## 📁 Estructura del Proyecto

```
GastosYPresupuesto/                # Raíz del proyecto Android
├── app/
│   ├── src/main/
│   │   ├── java/com/jfranco/gastosypresupuesto/   # Código fuente
│   │   │   ├── Components/       # Composables reutilizables (ej. gráficos)
│   │   │   ├── model/            # Clases de datos (Expense, Income)
│   │   │   ├── Navigation/       # NavHost y rutas
│   │   │   ├── repository/       # Repositorio (SettingsRepository)
│   │   │   ├── ui/               # Pantallas Compose
│   │   │   │   ├── AddTransactionScreen.kt
│   │   │   │   ├── DetailsScreen.kt
│   │   │   │   ├── HomeScreen.kt
│   │   │   │   ├── RegActivity.kt  # Pantalla de registro
│   │   │   │   ├── SettingsScreen.kt
│   │   │   │   └── StatsScreen.kt
│   │   │   ├── view/             # ViewModels (ExpenseViewModel, SettingsViewModel)
│   │   │   ├── HomeActivity.kt   # Login y punto de entrada
│   │   │   └── MainActivity.kt   # Host de la navegación tras login
│   │   └── res/                  # Layouts XML mínimos + iconos y valores
│   ├── google-services.json     # Config de Firebase (debe agregarse manualmente)
│   ├── build.gradle.kts         # Configuración de Gradle (Kotlin DSL)
│   └── proguard-rules.pro       # Reglas de minificación
└── settings.gradle.kts
```

---

## 📋 Requisitos Previos

- Android Studio (Arctic Fox o superior).
- Cuenta de Firebase creada.
- Proyecto de Firebase con **Authentication** habilitado (Email/Password).
- SHA-1/S HA-256 configurados en Firebase (para debug/release).

---

## ⚙️ Instalación y Configuración

1. Abre el proyecto en Android Studio.
2. Copia tu `google-services.json` (desde Firebase Console) dentro de `app/`.
3. Asegúrate de tener habilitado en **Authentication** > **Correo/Contraseña** en la consola de Firebase.
4. Sincroniza Gradle: Android Studio detectará los plugins de Firebase.

---

## 🔐 Flujo de Autenticación

1. **Pantalla de Login (HomeActivity):**
   - Se inicializa `FirebaseAuth.getInstance()`.
   - En el formulario se recogen `email` y `password`.
   - `auth.signInWithEmailAndPassword(email, password)`.
   - En `addOnSuccessListener` se navega a `MainActivity`.
   - En `addOnFailureListener` se muestra un `Toast` con el error.

2. **Pantalla de Registro (RegActivity):**
   - `auth.createUserWithEmailAndPassword(email, password)`.
   - Si es exitoso, se redirige a `HomeActivity` o al flujo principal.

3. **Protección de Rutas:**
   MainActivity actúa como Host de NavComponent; sólo se lanza tras login exitoso.

---

## 📱 Pantallas y Navegación

- **HomeActivity:** Inicio de sesión.
  ![image](https://github.com/user-attachments/assets/dd0b1780-cd9f-4a11-b97a-71d4356854e2)

- **RegActivity:** Registro de nuevos usuarios.
  ![image](https://github.com/user-attachments/assets/04531ae3-2764-4ba7-9115-2c2b8a66510a)

- **MainActivity + NavHost:** Barra de navegación inferior con:
  ![image](https://github.com/user-attachments/assets/7e261b80-8774-4611-8769-89d2984f3fd3)

  - **HomeScreen:** Resumen de gastos.
  - **AddTransactionScreen:** Formulario para agregar gasto/ingreso.
  - **StatsScreen:** Gráficos de distribución (ExpensePieChart).
  - **SettingsScreen:** Ajustes de usuario (SettingsViewModel + SettingsRepository).
  - **DetailsScreen:** Detalle de cada transacción.

---

## 🏗 Arquitectura

Patrón **MVVM**:

- **Model:** `Expense`, `Income`.
- **ViewModel:** `ExpenseViewModel` (gestiona CRUD en Firestore), `SettingsViewModel`.
- **Repository:** `SettingsRepository` (abstracción de almacenamiento local/ remoto).
- **View (UI):** Composables en `ui/`.

---

## ▶️ Ejecución

1. Conecta un emulador o dispositivo físico.
2. Ejecuta el proyecto desde Android Studio (Run ▶️ `app`).
3. Registra un usuario o inicia sesión.
4. Empieza a agregar y visualizar tus transacciones.

---

## 📄 Licencia

Este proyecto está bajo la **Licencia MIT**. Ver `LICENSE` para más detalles.

---

**Fecha de entrega parcial:** Abril 27

*Desarrollado por: Juan, Yulder, Diego y Franco*
