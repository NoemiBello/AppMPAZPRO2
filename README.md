
# MPAZ PRO 2

**Aplicación móvil Android desarrollada en Kotlin y Jetpack Compose, orientada a la gestión, validación y ejecución de contenidos académicos para Segundo Básico.**

---

## Descripción general

MPAZ PRO 2 centraliza la administración de contenidos, actividades y seguimiento académico mediante una arquitectura basada en roles.

La solución contempla perfiles diferenciados para **Jefatura UTP, Docente y Estudiante**, permitiendo gestionar contenidos, controlar su aprobación, registrar respuestas, supervisar actividades y consultar avances.

El proyecto fue desarrollado como un MVP funcional utilizando herramientas nativas del ecosistema Android, gestión de estado, persistencia local y una estructura modular basada en MVVM.

## Alcance funcional

La aplicación incorpora las siguientes capacidades:

- Gestión de asignaturas, unidades y objetivos de aprendizaje.
- Creación y edición de contenidos.
- Flujo de revisión y aprobación por Jefatura UTP.
- Gestión de estados de contenido.
- Registro y revisión de respuestas.
- Control de intentos por actividad.
- Seguimiento de progreso.
- Persistencia local de información.
- Integración de recursos nativos de Android.
- Navegación diferenciada según rol.

## Perfiles y permisos

### Jefatura UTP

Rol responsable de la administración y validación de contenidos.

**Principales funciones:**

- Acceso a las cuatro asignaturas del nivel.
- Consulta de unidades y objetivos de aprendizaje.
- Gestión de contenidos.
- Revisión de contenidos pendientes.
- Aprobación de contenidos.
- Rechazo de contenidos.
- Deshabilitación y reactivación de contenidos.
- Supervisión del estado general de los contenidos registrados.

### Docente

Rol orientado a la creación de contenidos y revisión de actividades.

**Principales funciones:**

- Consulta de unidades y objetivos de aprendizaje autorizados.
- Creación de contenidos.
- Edición de contenidos.
- Envío de contenidos a revisión.
- Asociación de imágenes de apoyo.
- Consulta de respuestas registradas.
- Revisión manual de respuestas.
- Clasificación de respuestas según estado.
- Seguimiento de intentos y resultados.

### Estudiante

Rol orientado a la consulta y ejecución de actividades disponibles.

**Principales funciones:**

- Acceso a contenidos habilitados.
- Navegación por asignatura, unidad y objetivo de aprendizaje.
- Consulta de actividades disponibles.
- Visualización de contenidos aprobados.
- Registro de respuestas.
- Consulta del estado de cada actividad.
- Visualización de intentos disponibles.
- Seguimiento de progreso.

## Flujo principal de contenidos

El proceso de gestión y aprobación de contenidos sigue el siguiente flujo:

```text
Docente crea contenido
        ↓
Contenido pendiente
        ↓
Jefatura UTP revisa
        ↓
Aprobación / Rechazo / Deshabilitación
        ↓
Contenido disponible según estado
        ↓
Estudiante accede al contenido aprobado
```

## Flujo de actividades

El proceso de ejecución de actividades contempla las siguientes etapas:

```text
Selección de rol
        ↓
Asignatura
        ↓
Unidad
        ↓
Objetivo de aprendizaje
        ↓
Actividad disponible
        ↓
Respuesta del estudiante
        ↓
Revisión del docente
        ↓
Actualización de estado y progreso
```

## Interfaz y navegación

La aplicación utiliza **Jetpack Compose y Material Design 3** para construir una interfaz modular y consistente.

**Características principales:**

- Navegación mediante Navigation Compose.
- Pantallas diferenciadas por rol.
- Componentes reutilizables.
- Barra de navegación adaptada al contexto.
- Menú lateral.
- Estados vacíos.
- Retroalimentación visual.
- Distribución jerárquica de información.
- Adaptabilidad mediante Window Size Classes.
- Scroll vertical en formularios.

## Formularios y validaciones

Los formularios implementan validaciones visuales y lógica desacoplada de la interfaz.

**Incluyen:**

- Validación de campos obligatorios.
- Mensajes de error por campo.
- Indicadores visuales mediante íconos.
- Retroalimentación inmediata.
- Manejo de estado desde ViewModel.
- Limpieza de errores durante la interacción.
- Validación previa al almacenamiento.

La lógica de validación se encuentra centralizada en la capa de ViewModel.

## Recursos nativos de Android

La aplicación integra recursos nativos de Android dentro del flujo funcional.

### Notificaciones

Se utilizan notificaciones del sistema para informar eventos asociados a la gestión y aprobación de contenidos.

La implementación considera el permiso correspondiente en versiones de Android que requieren autorización explícita.

### Selector de imágenes

El formulario de contenidos permite seleccionar imágenes desde el dispositivo mediante la Activity Result API.

La referencia de la imagen seleccionada se incorpora al estado del contenido y se gestiona dentro del flujo de persistencia local.

La aplicación no requiere cámara, GPS ni micrófono para su operación actual.

### Animaciones

La interfaz incorpora animaciones funcionales para mejorar la transición entre estados y la retroalimentación visual.

Se utilizan componentes como:

- `AnimatedVisibility`
- `AnimatedContent`
- `fadeIn`
- `fadeOut`

Las animaciones se aplican principalmente en cambios de estado y presentación dinámica de contenido.

## Arquitectura

El proyecto utiliza una arquitectura basada en **MVVM (Model-View-ViewModel)**, separando responsabilidades entre interfaz, lógica de negocio y persistencia.

**Componentes principales:**

- UI desarrollada con Jetpack Compose.
- ViewModels para gestión de estado y lógica.
- StateFlow para propagación reactiva de cambios.
- Modelos de estado para contenidos y respuestas.
- DataStore para persistencia local.
- Navigation Compose para gestión de rutas.
- Componentes reutilizables para elementos comunes de interfaz.

## Persistencia local

La aplicación utiliza DataStore para mantener información de forma local.

Actualmente se almacenan:

- Contenidos.
- Estados de contenido.
- Preguntas.
- Respuestas.
- Estados de respuestas.
- Intentos registrados.
- Referencias de imágenes.
- Información necesaria para reconstruir el flujo funcional de la aplicación.

## Gestión de estado

La aplicación utiliza StateFlow para mantener sincronizada la interfaz con los cambios de estado.

Esto permite:

- Actualización reactiva de vistas.
- Separación entre lógica y presentación.
- Control centralizado del estado de formularios.
- Gestión de contenidos.
- Gestión de respuestas.
- Actualización de progreso.

## Tecnologías utilizadas

| **Tecnología** | **Uso** |
| --- | --- |
| Kotlin | Lenguaje de programación |
| Android Studio | Entorno de desarrollo |
| Jetpack Compose | Desarrollo de interfaces |
| Material Design 3 | Diseño visual |
| MVVM | Arquitectura |
| Navigation Compose | Navegación |
| ViewModel | Gestión de lógica y estado |
| StateFlow | Gestión reactiva del estado |
| Kotlin Coroutines | Programación asíncrona |
| DataStore Preferences | Persistencia local |
| Activity Result API | Selección de imágenes |
| Git | Control de versiones |
| GitHub | Repositorio y colaboración |

## Estructura del proyecto

La organización principal del proyecto es la siguiente:

```text
app/
└── src/
    └── main/
        └── java/
            └── com.example.appmpazpro2/
                ├── data/
                ├── model/
                ├── navigation/
                ├── ui/
                │   ├── components/
                │   ├── screens/
                │   ├── theme/
                │   └── utils/
                └── viewmodel/
```

## Principales módulos funcionales

- Selección de rol.
- Inicio Jefatura UTP.
- Inicio Docente.
- Inicio Estudiante.
- Gestión de asignaturas.
- Gestión de unidades.
- Gestión de objetivos de aprendizaje.
- Gestión de contenidos.
- Formulario de contenidos.
- Revisión de contenidos.
- Actividades disponibles.
- Registro de respuestas.
- Revisión de respuestas.
- Seguimiento de progreso.
- Registro de usuario.

## Instalación y ejecución

### Requisitos

- Android Studio.
- Android SDK compatible.
- JDK configurado según el proyecto.
- Emulador Android o dispositivo físico.

### Clonar repositorio

```bash
git clone https://github.com/NoemiBello/AppMPAZPRO2.git
```

### Ejecución

1. Abrir el proyecto en Android Studio.
2. Esperar la sincronización de Gradle.
3. Seleccionar un emulador o dispositivo Android.
4. Ejecutar el módulo `app`.
5. Seleccionar uno de los roles disponibles:
   - Jefatura UTP.
   - Docente.
   - Estudiante.

## Control de versiones

El proyecto utiliza Git y GitHub para control de versiones y trabajo colaborativo.

**Ramas de trabajo:**

- `master`
- `noemi-bello`
- `jequito`

Los cambios se desarrollan en ramas individuales y posteriormente se integran a la rama principal mediante Pull Requests.

## Trabajo colaborativo

El desarrollo fue realizado en equipo mediante una distribución de tareas asociadas a:

- Desarrollo de interfaces.
- Implementación de navegación.
- Gestión de estado.
- Persistencia local.
- Validaciones.
- Pruebas funcionales.
- Corrección de errores.
- Integración de funcionalidades.
- Control de versiones.

La planificación y seguimiento del trabajo se complementa mediante herramientas colaborativas como GitHub y Trello.

## Autores

- **Noemi Bello Nanculeo**
- **Jeanpiere Adrian Quito Vidal**

## Estado del proyecto

**MPAZ PRO 2** corresponde a un MVP funcional desarrollado para la asignatura **Desarrollo de Aplicaciones Móviles — DSY1105**.

La versión actual integra navegación por roles, gestión de contenidos, validaciones, persistencia local, recursos nativos, animaciones, control de respuestas y seguimiento de progreso, dejando una base modular preparada para futuras ampliaciones funcionales.

---

**Proyecto académico | Desarrollo de Aplicaciones Móviles | DSY1105**
