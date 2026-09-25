# LenguajeCafetero

Sistema de gestión de la academia de idiomas **LenguajeCafetero**.  
**Parcial I — Programación II** (Noche).

Repositorio: [github.com/julianamaya1928/Parcial1](https://github.com/julianamaya1928/Parcial1)

---

## Qué es este proyecto

Aplicación de escritorio en **Java 17 + JavaFX 21** con arquitectura **MVC** para administrar:

- Estudiantes, profesores y servicios adicionales  
- Cursos (**Regular**, **Intensivo**, **Personalizado**)  
- Matrículas con cálculo de valor  
- Consultas obligatorias: búsqueda por documento (RF-07) e ingresos por periodo (RF-08)

---

## Requisitos

| Herramienta | Versión |
|-------------|---------|
| **JDK** | 17+ (probado con 22) |
| **Maven** | 3.9+ |
| **JavaFX** | 21 (vía dependencias Maven) |

---

## Cómo ejecutar

```bash
cd Parcial1
mvn clean javafx:run
```

Compilar:

```bash
mvn clean compile
```

Pruebas unitarias del modelo:

```bash
mvn test
```

Al iniciar se cargan **datos de demostración** (`DemoData`) para poder probar CRUD y consultas de inmediato.

---

## Estructura del repositorio

```
Parcial1/
├── pom.xml
├── README.md
├── docs/                          # Análisis, diagrama, plan, Word
│   ├── 00-contexto-parcial.md
│   ├── 01-analisis-problema.md
│   ├── 02-plan-por-fases.md
│   ├── 03-solid-y-patrones.md
│   ├── 04-checklist-entrega.md
│   ├── Parcial1_Analisis_LenguajeCafetero.docx
│   └── diagrama-clases-*.drawio / .svg
└── src/
    ├── main/java/com/lenguajecafetero/
    │   ├── MainApp.java           # Entry point: crea Academia + DemoData
    │   ├── model/                 # Dominio, Factory, Builder, Academia
    │   ├── view/                  # (reservado; UI en FXML)
    │   └── controller/            # MainController (eventos → modelo)
    ├── main/resources/
    │   ├── fxml/MainView.fxml
    │   └── css/app.css
    └── test/java/.../model/       # 22 tests JUnit 5
```

---

## Arquitectura MVC

| Capa | Ubicación | Responsabilidad |
|------|-----------|-----------------|
| **Model** | `model/` | Entidades, reglas de tarifa, Factory, Builder, Academia |
| **View** | `resources/fxml`, `css` | Pantallas y estilos |
| **Controller** | `controller/MainController` | Eventos UI → llamadas al modelo |

La GUI **no** calcula tarifas ni ingresos: solo muestra lo que devuelve el modelo.

---

## Patrones creacionales

| Patrón | Clase | Uso |
|--------|--------|-----|
| **Factory** | `CursoFactory` | Crea `CursoRegular` / `CursoIntensivo` / `CursoPersonalizado` |
| **Builder** | `MatriculaBuilder` | Arma matrículas con servicios, descuento y profesor opcional |

Detalle y SOLID: [`docs/03-solid-y-patrones.md`](docs/03-solid-y-patrones.md).

---

## Jerarquía de cursos (polimorfismo)

```
Curso (abstracta) — calcularCosto(meses)
 ├── CursoRegular        → valorMensual × meses
 ├── CursoIntensivo      → valorMensual × meses × factorIntensidad
 └── CursoPersonalizado  → valorMensual × meses
                          (+ sesiones × tarifa en Matricula)
```

---

## Consultas del enunciado

| RF | Método en `Academia` | UI |
|----|----------------------|-----|
| RF-07 | `buscarEstudiantePorDocumento(doc)` | Pestaña Consultas |
| RF-08 | `calcularIngresosPorPeriodo(ini, fin)` | Pestaña Consultas |

---

## Estado del desarrollo

| Paso | Estado |
|------|--------|
| 1. Análisis escrito | ✅ `docs/` |
| 2. Diagrama de clases | ✅ `docs/` |
| 3. Java + SOLID + creacionales | ✅ modelo + tests |
| 4. JavaFX + MVC | ✅ shell + CRUD + consultas |
| 5. Entrega (video, Classroom, revocar token) | ⏳ checklist en `docs/04-checklist-entrega.md` |

---

## Integrante(s)

- **Julian Amaya** — `juliancitoamayag@gmail.com`  
  (Completar C.C. y segundo integrante si aplica.)

---

## Convenciones git

- Commits en **español**
- Identidad personal solo en este trabajo (perfil VS Code **Julian**)
- **Nunca** `git config --global` en el PC de la empresa
