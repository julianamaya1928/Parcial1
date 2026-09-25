# LenguajeCafetero

Sistema de gestión de la academia de idiomas **LenguajeCafetero**.  
**Parcial I — Programación II** (Noche).

Repositorio: [github.com/julianamaya1928/Parcial1](https://github.com/julianamaya1928/Parcial1)

---

## Qué es este proyecto

Aplicación de escritorio en **Java + JavaFX** con arquitectura **MVC** para administrar:

- Estudiantes  
- Cursos (Regular, Intensivo, Personalizado)  
- Profesores  
- Matrículas  
- Servicios adicionales  
- Consultas (búsqueda por documento, ingresos por periodo)

---

## Estructura del repositorio

```
Parcial1/
├── pom.xml                          # Maven + JavaFX
├── README.md
├── .gitignore
└── src/
    ├── main/
    │   ├── java/com/lenguajecafetero/
    │   │   ├── MainApp.java         # Punto de entrada JavaFX
    │   │   ├── model/               # Dominio (vacío por ahora)
    │   │   ├── view/                # Auxiliares de vista
    │   │   └── controller/          # Controladores JavaFX
    │   └── resources/
    │       ├── fxml/                # Pantallas FXML
    │       ├── css/                 # Estilos
    │       └── images/
    └── test/java/com/lenguajecafetero/
```

### Documentación del parcial

La documentación (análisis, diagrama, Word) vive **fuera** de este repo, en:

```
../docs/   →  /Users/oscar/Desktop/Oscar/Julian/docs/
```

| Archivo | Contenido |
|---------|-----------|
| `00-contexto-parcial.md` | Brújula del parcial |
| `01-analisis-problema.md` | Análisis (punto 1) |
| `Parcial1_Analisis_LenguajeCafetero.docx` | Entregable Word |
| `diagrama-clases-lenguajecafetero.drawio` | Diagrama UML |

---

## Requisitos

| Herramienta | Versión |
|-------------|---------|
| **JDK** | 17 o superior (probado con 22) |
| **Maven** | 3.9+ |
| **JavaFX** | 21 (vía Maven) |

---

## Cómo ejecutar

```bash
cd Parcial1
mvn clean javafx:run
```

Compilar sin abrir la UI:

```bash
mvn clean compile
```

Tests:

```bash
mvn test
```

---

## Arquitectura MVC

| Capa | Paquete / carpeta | Responsabilidad |
|------|-------------------|-----------------|
| **Model** | `com.lenguajecafetero.model` | Entidades, reglas, factories, builders |
| **View** | `resources/fxml`, `resources/css` | Interfaz gráfica |
| **Controller** | `com.lenguajecafetero.controller` | Eventos UI → llamadas al modelo |

La GUI **no** calcula tarifas ni ingresos; eso vive en el modelo.

---

## Estado del desarrollo

| Paso | Estado |
|------|--------|
| 1. Análisis escrito | ✅ (en `../docs/`) |
| 2. Diagrama de clases | ✅ (en `../docs/`) |
| 3. Base del proyecto (este commit) | ✅ |
| 4. Modelo Java + SOLID + creacionales | ⏳ Pendiente |
| 5. GUI JavaFX completa | ⏳ Pendiente |
| 6. Entrega (commits, video) | ⏳ Pendiente |

---

## Convenciones

- Paquete base: `com.lenguajecafetero`
- Java 17+
- Sin `git config --global` en este PC (usar identidad local del perfil VS Code **Julian**)
