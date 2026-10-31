# MPAZ PRO 2

Aplicación móvil de apoyo didáctico para estudiantes de Segundo Básico, desarrollada en el contexto de la asignatura **DSY1105 – Desarrollo de Aplicaciones Móviles** de Duoc UC.

## Descripción

MPAZ PRO 2 es un Producto Mínimo Viable orientado a apoyar la gestión y ejecución de contenidos pedagógicos para Segundo Básico.

La solución surge a partir del caso de la Escuela Escritora Marcela Paz, donde gran parte de los contenidos, actividades y evaluaciones se gestionan mediante material impreso, dificultando la trazabilidad, el registro de avances y la centralización de información pedagógica.

El proyecto propone una aplicación móvil que organiza asignaturas, unidades, objetivos de aprendizaje, contenidos y actividades, incorporando perfiles diferenciados para Jefatura UTP, Docente y Estudiante.

## Integrantes

- Noemi Bello Nanculeo
- Jeanpiere Adrian Quito Vidal

## Objetivo del proyecto

Desarrollar una aplicación móvil funcional que permita:

- Organizar contenidos pedagógicos de Segundo Básico.
- Gestionar asignaturas, unidades y objetivos de aprendizaje.
- Diferenciar funcionalidades y permisos según el perfil de usuario.
- Facilitar la creación, revisión y visualización de contenidos.
- Permitir el registro de respuestas y seguimiento de progreso.
- Mantener información mediante persistencia local.
- Entregar una interfaz clara, coherente y adaptada a estudiantes de Segundo Básico.

## Perfiles de usuario

### Jefatura UTP

Perfil responsable de la gestión pedagógica general del sistema.

Entre sus funciones se consideran:

- Visualización de las asignaturas del nivel.
- Gestión de contenidos pedagógicos.
- Revisión de estados de contenidos.
- Aprobación, rechazo u ocultamiento de contenidos según la lógica definida en el MVP.

### Docente

Perfil orientado a la gestión y utilización de contenidos pedagógicos.

Entre sus funciones se consideran:

- Consulta de unidades y objetivos de aprendizaje.
- Revisión de recursos y contenidos.
- Creación y edición de contenidos.
- Gestión de actividades asociadas a la asignatura definida para el MVP.
- Revisión de información relacionada con el avance del estudiante.

### Estudiante

Perfil orientado a la ejecución de contenidos y actividades.

Entre sus funciones se consideran:

- Acceso a asignaturas habilitadas.
- Consulta de unidades y objetivos de aprendizaje.
- Visualización de recursos y contenidos.
- Respuesta de actividades.
- Consulta de información de progreso.

## Flujo principal

El flujo principal considerado para la aplicación es:

```text
Selección de perfil
        ↓
Asignatura
        ↓
Unidad
        ↓
Objetivo de aprendizaje
        ↓
Recursos / Contenidos
        ↓
Actividad / Evaluación
        ↓
Respuesta / Revisión
```

## Funcionalidades implementadas

### Interfaz y navegación

- Selección de perfil.
- Pantalla de inicio.
- Navegación entre vistas.
- Menú lateral.
- Barra de navegación inferior.
- Pantalla de asignaturas.
- Pantalla de unidades.
- Pantalla de objetivos de aprendizaje.
- Pantalla de recursos.
- Pantalla de progreso.

### Formularios y validaciones

- Formulario para creación y edición de contenido.
- Validaciones visuales por campo.
- Mensajes de error.
- Retroalimentación visual ante datos inválidos.

### Diseño

- Interfaz desarrollada con Jetpack Compose.
- Componentes Material Design 3.
- Tema visual propio.
- Diseño adaptable mediante Window Size Classes.
- Uso de tarjetas, botones e íconos para mejorar jerarquía visual y usabilidad.
- Interfaz orientada a lectores iniciales de Segundo Básico.

### Arquitectura y estado

- Arquitectura MVVM.
- Uso de ViewModel.
- Manejo de estado de interfaz.
- Separación de responsabilidades entre lógica, datos y presentación.

### Persistencia

- Uso de DataStore para almacenamiento local.
- Persistencia de información relacionada con contenidos, respuestas y estados del MVP.

## Tecnologías utilizadas

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

## Estructura general del proyecto

```text
app/
└── src/
    └── main/
        └── java/
            └── com.example.appmpazpro2/
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
- Asignaturas
- Unidades
- Objetivos de aprendizaje
- Recursos
- Formulario de contenido
- Contenido del estudiante
- Progreso

## Criterios de diseño para Segundo Básico

La interfaz se diseñó considerando los requerimientos del caso:

- Botones e íconos legibles.
- Tipografía de tamaño adecuado.
- Oraciones breves.
- Baja carga cognitiva por pantalla.
- Navegación simple.
- Diferenciación visual entre secciones.
- Adaptación a teléfonos y tabletas Android.

## Ejecución del proyecto

### Requisitos

- Android Studio.
- Android SDK.
- JDK compatible con la configuración del proyecto.
- Emulador Android o dispositivo físico.
- Conexión a Internet para la descarga inicial de dependencias.

### Pasos de ejecución

1. Clonar el repositorio:

```bash
git clone https://github.com/NoemiBello/AppMPAZPRO2.git
```

2. Abrir Android Studio.

3. Seleccionar **Open** y abrir la carpeta del proyecto.

4. Esperar la sincronización de Gradle.

5. Seleccionar un emulador o dispositivo Android.

6. Ejecutar el módulo `app`.

7. Iniciar el flujo seleccionando uno de los perfiles disponibles:
   - Jefatura UTP
   - Docente
   - Estudiante

## Control de versiones

El proyecto utiliza Git y GitHub para el desarrollo colaborativo.

Se trabaja con ramas independientes por integrante y posterior integración mediante Pull Requests.

Ramas utilizadas:

```text
master
noemi-bello
jequito
```

## Distribución del trabajo

### Noemi Bello Nanculeo

Responsable principalmente de:

- Diseño de pantallas.
- Interfaz Material Design 3.
- Selección de perfil.
- Asignaturas.
- Unidades.
- Objetivos de aprendizaje.
- Recursos.
- Formulario de contenido.
- Pantalla de contenido del estudiante.
- Validaciones visuales.
- Adaptabilidad de interfaz.
- Tema y colores de la aplicación.

### Jeanpiere Adrian Quito Vidal

Responsable principalmente de:

- Lógica de roles.
- ViewModels.
- Navegación.
- Gestión de permisos.
- Persistencia local.
- Estados de contenidos.
- Lógica de respuestas.
- Integración funcional entre pantallas.

## Contexto académico

Proyecto desarrollado para la asignatura:

**DSY1105 – Desarrollo de Aplicaciones Móviles**

**Duoc UC**

Evaluación Parcial 2 – Encargo.

El proyecto corresponde a un MVP académico y utiliza información ficticia o anonimizada para fines de prueba y demostración.
