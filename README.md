# MPAZ PRO 2

Aplicación móvil de apoyo didáctico para estudiantes de Segundo Básico, desarrollada en **Kotlin y Jetpack Compose**.

## Descripción

**MPAZ PRO 2** es una aplicación móvil orientada a centralizar y digitalizar la gestión de contenidos pedagógicos para Segundo Básico.

La plataforma permite organizar asignaturas, unidades, objetivos de aprendizaje, contenidos y actividades mediante perfiles diferenciados para **Jefatura UTP, Docente y Estudiante**, facilitando la administración pedagógica, la revisión de contenidos y el seguimiento de actividades.

El sistema fue diseñado considerando una experiencia simple, clara y de baja carga cognitiva, adecuada para estudiantes en etapa inicial de lectura.

## Objetivos

- Centralizar contenidos pedagógicos de Segundo Básico.
- Gestionar asignaturas, unidades y objetivos de aprendizaje.
- Diferenciar permisos y funcionalidades según el perfil de usuario.
- Implementar un flujo de creación, revisión y aprobación de contenidos.
- Permitir a los estudiantes responder actividades y consultar su progreso.
- Permitir a los docentes revisar respuestas y estados de actividades.
- Mantener información mediante persistencia local.
- Proporcionar una interfaz clara y adaptable a dispositivos Android.

## Perfiles de usuario

### Jefatura UTP

Perfil responsable de la administración pedagógica general.

Funciones principales:

- Acceso a las cuatro asignaturas del nivel.
- Consulta de unidades y objetivos de aprendizaje.
- Gestión de contenidos pedagógicos.
- Revisión de contenidos pendientes.
- Aprobación, rechazo y deshabilitación de contenidos.

### Docente

Perfil orientado a la creación de contenidos y seguimiento de actividades.

Funciones principales:

- Consulta de unidades y objetivos de aprendizaje.
- Creación y edición de contenidos.
- Envío de contenidos para revisión.
- Consulta de respuestas de estudiantes.
- Revisión y clasificación de respuestas.

### Estudiante

Perfil orientado a la ejecución de contenidos y actividades.

Funciones principales:

- Acceso a contenidos habilitados.
- Navegación por unidades y objetivos de aprendizaje.
- Visualización de actividades aprobadas.
- Selección de actividades disponibles.
- Envío de respuestas.
- Consulta del estado de cada actividad.
- Visualización de progreso.

## Flujo funcional

### Gestión de contenidos

```text
Docente crea contenido
        ↓
Contenido pendiente
        ↓
Jefatura UTP revisa
        ↓
Aprobado / Rechazado / Deshabilitado
        ↓
El estudiante accede únicamente al contenido aprobado
```

### Flujo del estudiante

```text
Selección de perfil
        ↓
Asignatura
        ↓
Unidad
        ↓
Objetivo de aprendizaje
        ↓
Actividades disponibles
        ↓
Contenido / Pregunta
        ↓
Respuesta
        ↓
Revisión del docente
```

## Funcionalidades

### Navegación e interfaz

- Selección de perfil.
- Pantallas de inicio diferenciadas por rol.
- Navegación mediante Navigation Compose.
- Barra de navegación inferior adaptada al perfil.
- Menú lateral.
- Gestión de asignaturas, unidades y objetivos de aprendizaje.
- Gestión de contenidos.
- Listado de actividades para estudiantes.
- Pantalla de respuesta del estudiante.
- Revisión de respuestas.
- Seguimiento de progreso.
- Perfil y registro de usuario.

### Gestión por roles

- Control de acceso según perfil.
- Jefatura UTP con acceso a las cuatro asignaturas.
- Docente con acceso a las funcionalidades de gestión y revisión.
- Estudiante con acceso restringido a contenidos habilitados.
- Navegación y opciones ajustadas dinámicamente según el rol seleccionado.

### Contenidos y actividades

- Creación y edición de contenidos.
- Asociación con asignatura, unidad y objetivo de aprendizaje.
- Gestión de título, tipo, contenido y pregunta.
- Estados de aprobación.
- Visualización exclusiva de contenidos aprobados para estudiantes.
- Soporte para múltiples actividades por objetivo de aprendizaje.
- Envío y almacenamiento de respuestas.
- Control de intentos.
- Revisión manual por parte del docente.
- Estados de actividad y respuesta.

### Formularios y validaciones

- Formularios con validaciones visuales.
- Mensajes de error.
- Retroalimentación ante información inválida.
- Manejo del estado mediante ViewModel.
- Scroll vertical para mantener la usabilidad durante la interacción con el teclado.

## Arquitectura

El proyecto utiliza una arquitectura basada en **MVVM**, separando responsabilidades entre presentación, lógica y persistencia.

Principales componentes:

- ViewModel.
- StateFlow.
- Navigation Compose.
- DataStore.
- Componentes reutilizables.
- Separación entre capas de datos, navegación y presentación.

## Persistencia local

La aplicación utiliza **DataStore** para almacenar información relacionada con:

- Contenidos pedagógicos.
- Respuestas.
- Estados de contenidos.
- Estados de actividades.
- Información necesaria para el flujo principal de la aplicación.

## Diseño y experiencia de usuario

La interfaz fue desarrollada con **Jetpack Compose** y **Material Design 3**.

Principales criterios considerados:

- Botones e íconos legibles.
- Tipografía clara.
- Textos breves.
- Baja carga cognitiva por pantalla.
- Jerarquía visual consistente.
- Estados vacíos para contenido no disponible.
- Retroalimentación visual.
- Adaptabilidad a distintos tamaños de pantalla mediante Window Size Classes.

## Tecnologías

- Kotlin
- Android Studio
- Jetpack Compose
- Material Design 3
- MVVM
- Navigation Compose
- ViewModel
- StateFlow
- DataStore
- Git
- GitHub

## Estructura del proyecto

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

## Principales pantallas

- Selección de perfil
- Inicio
- Inicio Jefatura UTP
- Inicio Docente
- Asignaturas
- Unidades
- Objetivos de aprendizaje
- Gestión de contenidos
- Formulario de contenido
- Actividades disponibles
- Contenido del estudiante
- Revisión de respuestas
- Progreso
- Perfil
- Registro

## Instalación y ejecución

### Requisitos

- Android Studio.
- Android SDK.
- JDK compatible con la configuración del proyecto.
- Emulador Android o dispositivo físico.

### Clonar el repositorio

```bash
git clone https://github.com/NoemiBello/AppMPAZPRO2.git
```

### Ejecución

1. Abrir el proyecto en Android Studio.
2. Esperar la sincronización de Gradle.
3. Seleccionar un emulador o dispositivo Android.
4. Ejecutar el módulo `app`.
5. Seleccionar uno de los perfiles disponibles:
   - Jefatura UTP
   - Docente
   - Estudiante

## Control de versiones

El proyecto utiliza **Git y GitHub** para control de versiones y desarrollo colaborativo.

Ramas principales:

```text
master
noemi-bello
jequito
```

Cada integrante trabaja sobre una rama independiente y los cambios se integran posteriormente a la rama principal mediante control de versiones.

## Autores

**Noemi Bello Nanculeo**  
**Jeanpiere Adrian Quito Vidal**

## Estado del proyecto

MPAZ PRO 2 se encuentra en desarrollo activo como una solución móvil orientada a la gestión y ejecución de contenidos pedagógicos para Segundo Básico.
