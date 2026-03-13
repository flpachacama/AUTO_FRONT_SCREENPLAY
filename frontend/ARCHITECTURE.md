# React Project Architecture

This project follows a modular architecture designed for scalability and maintainability.

## 📁 Directory Structure

```
src/
├── assets/              # Static assets
│   ├── images/         # Images and media files
│   ├── icons/          # Icon files
│   └── styles/         # Global styles, Tailwind config
│
├── components/          # Reusable UI components
│   ├── common/         # Shared components (Button, Input, Modal, etc.)
│   ├── forms/          # Form-specific components using react-hook-form
│   ├── layouts/        # Layout components (Header, Footer, Sidebar)
│   └── ui/             # PrimeReact wrapper components
│
├── hooks/              # Custom React hooks
│   ├── forms/          # Form-related hooks (useFormValidation, useFieldArray)
│   ├── state/          # Zustand store hooks (useAuthStore, useUserStore)
│   └── data/           # Data fetching hooks (useQuery, useMutation)
│
├── pages/              # Page components (route views)
│   ├── auth/           # Authentication pages (Login, Register, ForgotPassword)
│   ├── dashboard/      # Dashboard page
│   ├── users/          # User management pages
│   └── public/         # Public pages (Home, About, Contact)
│
├── routes/             # Routing configuration
│   └── index.tsx       # Main router setup (React Router)
│
├── services/           # Business logic & API integration
│   ├── api/            # API client configuration & endpoints
│   ├── auth/           # Authentication service
│   └── validation/     # Validation helpers
│
├── schemas/            # Zod validation schemas
│   ├── userSchema.ts   # Example: User validation schema
│   └── authSchema.ts   # Example: Auth validation schema
│
├── store/              # Zustand state management
│   ├── slices/         # Store slices (authSlice, userSlice)
│   └── index.ts        # Store configuration
│
├── types/              # TypeScript type definitions
│   ├── models/         # Data models (User, Product, etc.)
│   └── api/            # API response types
│
└── utils/              # Utility functions
    ├── formatters/     # Data formatters (dates, currency, etc.)
    ├── validators/     # Custom validators
    └── helpers/        # General helper functions
```

## 🛠️ Technology Stack

### UI & Styling
- **PrimeReact**: Component library for rich UI components
- **Tailwind CSS**: Utility-first CSS framework

### State Management
- **Zustand**: Lightweight state management

### Forms & Validation
- **React Hook Form**: Performant forms with easy validation
- **Zod**: TypeScript-first schema validation

## 📋 Directory Usage Guidelines

### `/components`
Place all reusable UI components here:
- **common/**: Generic components like Button, Card, Badge
- **forms/**: Form fields integrated with react-hook-form
- **layouts/**: Page layouts and structural components
- **ui/**: PrimeReact component wrappers with custom styling

### `/hooks`
Custom React hooks organized by purpose:
- **forms/**: `useFormWithSchema`, `useFieldValidation`
- **state/**: Zustand store hooks like `useAuthStore`, `useCartStore`
- **data/**: API hooks like `useUsers`, `useProducts`

### `/pages`
Each page component represents a route:
- Group related pages in subdirectories
- Keep pages thin - delegate logic to hooks and services

### `/routes`
Routing configuration:
- Main router setup
- Route guards and protected routes
- Route constants

### `/services`
Business logic and external integrations:
- **api/**: HTTP client setup, interceptors, endpoints
- **auth/**: Authentication logic (login, logout, token management)
- **validation/**: Custom validation utilities

### `/schemas`
Zod schemas for data validation:
- Define schemas for forms, API requests/responses
- Reusable validation logic
- Type inference from schemas

### `/store`
Zustand state management:
- **slices/**: Individual store slices for different domains
- Each slice handles its own state and actions
- Combine slices in main store

### `/types`
TypeScript type definitions:
- **models/**: Domain models (User, Product, Order)
- **api/**: API request/response types
- Shared interfaces and enums

### `/utils`
Pure utility functions:
- **formatters/**: Date, currency, string formatters
- **validators/**: Custom validation functions
- **helpers/**: General-purpose utilities

## 🎯 Example Usage Patterns

### Form with Validation (react-hook-form + zod)
```typescript
// schemas/loginSchema.ts
// services/api/authService.ts
// hooks/forms/useLoginForm.ts
// pages/auth/Login.tsx
```

### State Management (Zustand)
```typescript
// store/slices/authSlice.ts
// hooks/state/useAuthStore.ts
// pages/dashboard/Dashboard.tsx
```

### API Integration
```typescript
// services/api/client.ts
// services/api/userService.ts
// hooks/data/useUsers.ts
// pages/users/UserList.tsx
```

### Component with PrimeReact + Tailwind
```typescript
// components/ui/CustomButton.tsx (PrimeReact Button + Tailwind)
// components/common/UserCard.tsx (uses CustomButton)
```

## 🚀 Best Practices

1. **Single Responsibility**: Each file should have one clear purpose
2. **Co-location**: Keep related files close together
3. **Barrel Exports**: Use index.ts files for clean imports
4. **Type Safety**: Leverage TypeScript and Zod for runtime validation
5. **Separation of Concerns**: Keep UI, logic, and state separate
6. **Reusability**: Build components and hooks to be reusable
7. **Consistent Naming**: Use clear, descriptive names

## 📦 Dependencies Structure

- **PrimeReact** → `components/ui/` wrapper components
- **Tailwind CSS** → `assets/styles/` + inline classes
- **React Hook Form** → `hooks/forms/` + `pages/`
- **Zod** → `schemas/` validation schemas
- **Zustand** → `store/slices/` + `hooks/state/`

```

---

## 🎭 QA Automation — Serenity BDD + Screenplay Pattern

The test automation layer lives under `src/test/java/automation/` and follows the
**Screenplay Pattern** exclusively. Every class has a single, clearly-named
responsibility; no UI locators appear inside Tasks or Step Definitions.

### Package Structure

```
src/test/java/automation/
├── hooks/                          # Cucumber lifecycle hooks
│   └── SetupHooks.java             # @Before → OnStage.setTheStage()  |  @After → drawTheCurtain()
│
├── questions/                      # Screenplay Questions — read application state
│   └── KudoSubmissionResult.java   # Answers: is the '¡Kudo enviado!' toast visible?
│
├── runners/                        # JUnit + Serenity entry points
│   └── KudoTestRunner.java         # @RunWith(CucumberWithSerenity) + @CucumberOptions
│
├── stepdefinitions/                # Cucumber glue — thin orchestration only
│   └── KudoStepDefinitions.java    # actor.attemptsTo(...) / actor.should(seeThat(...))
│
├── task/                           # Screenplay Tasks & Interactions — one action each
│   ├── OpenLandingPage.java        # Task        → opens the app root URL
│   ├── NavigateToKudosForm.java    # Task        → clicks 'Acceder', waits for form
│   ├── SelectFromUser.java         # Task        → chooses sender from dropdown
│   ├── SelectToUser.java           # Task        → chooses recipient from dropdown
│   ├── SelectCategory.java         # Task        → picks a recognition category
│   ├── EnterKudoMessage.java       # Task        → clears + types the message field
│   ├── SubmitKudoWithSlider.java   # Task        → waits for slider, delegates drag
│   └── DragSliderToEnd.java        # Interaction → executes the physical drag gesture
│
├── ui/                             # Serenity Target registry — all locators live here
│   └── KudoFormUI.java             # Target constants + LandingHomePage (@DefaultUrl)
│
└── util/                           # Low-level reusable helpers
    ├── BrowserUtils.java           # getDriverFor(), scrollToTop(), waitForPageLoad()
    ├── WaitUtils.java              # isElementVisible/Invisible/Clickable() with timeout
    └── SliderActions.java          # dragToEnd() — Selenium Actions drag + keyboard fallback
```

### Screenplay Pyramid

```
┌───────────────────────────────────────┐
│          StepDefinitions              │  ← Orchestration only (attemptsTo / should)
├───────────────────────────────────────┤
│       Tasks  (business level)         │  ← One business action each (Task interface)
├───────────────────────────────────────┤
│   Interactions  (browser level)       │  ← One gesture each (Interaction / Performable)
├───────────────────────────────────────┤
│     UI Targets  +  Util helpers       │  ← Locators & raw WebDriver utilities
└───────────────────────────────────────┘
```

### Layer Responsibilities

| Layer | Class | Responsibility |
|---|---|---|
| **Hooks** | `SetupHooks` | Set up / tear down the Screenplay Stage per scenario |
| **Questions** | `KudoSubmissionResult` | Read DOM state; return a typed `Boolean` answer |
| **Runners** | `KudoTestRunner` | Wire Cucumber feature files to the glue packages |
| **Step Definitions** | `KudoStepDefinitions` | Call `attemptsTo()` / `should(seeThat(...))` — nothing else |
| **Tasks** | `OpenLandingPage`, `SelectFromUser`, … | Each wraps exactly one business action |
| **Interactions** | `DragSliderToEnd` | Execute one raw browser gesture via `SliderActions` |
| **UI** | `KudoFormUI` | Declare all `Target` constants and `@DefaultUrl` page entry points |
| **Util** | `BrowserUtils`, `WaitUtils`, `SliderActions` | Stateless helpers — never import Task or Question classes |

### Key Design Rules

1. **No locators in Tasks** — all `Target` constants live exclusively in `KudoFormUI`.
2. **No business logic in Step Definitions** — they only delegate to Tasks/Questions.
3. **Explicit waits in Questions** — `WaitUtils.isElementVisible()` is called inside `answeredBy()`.
4. **Factory methods on every class** — `OpenLandingPage.open()`, `SelectFromUser.named("…")`, etc.
5. **`@Step` annotations** — every `performAs()` / `answeredBy()` method is annotated for Serenity HTML reports.
6. **Private constructors** — all Task/Interaction/Question classes enforce instantiation via factory method only.

### Data Flow — "Submit a valid kudo" Scenario

```
Feature File (send_kudo.feature)
  │
  ▼
KudoTestRunner           ← @RunWith(CucumberWithSerenity)
  │
  ▼
SetupHooks.setUpStage()  ← OnStage.setTheStage(new OnlineCast())
  │
  ▼
KudoStepDefinitions
  ├─ Given → actor.attemptsTo( OpenLandingPage.open() )
  │              └─ Open.browserOn( LandingHomePage )  [webdriver.base.url + "/"]
  │
  ├─ When  → actor.attemptsTo( NavigateToKudosForm.now() )
  │              └─ Click LANDING_ACCESS_BUTTON → WaitUntil KUDOS_FORM_TITLE isVisible
  │
  ├─ When  → actor.attemptsTo( SelectFromUser, SelectToUser, SelectCategory, EnterKudoMessage )
  │              └─ SelectFromOptions → FROM_USER / TO_USER / CATEGORY / MESSAGE (KudoFormUI)
  │
  ├─ When  → actor.attemptsTo( SubmitKudoWithSlider.now() )
  │              └─ WaitUntil SLIDER_TRACK/HANDLE → DragSliderToEnd.now()
  │                     └─ SliderActions.dragToEnd(actor)  [Selenium Actions + keyboard fallback]
  │
  └─ Then  → actor.should( seeThat( KudoSubmissionResult.isVisible(), is(true) ) )
                 └─ WaitUtils.isElementVisible( driver, "Kudo enviado", 8s )
```

### Running the Suite

```bash
# Full suite + Serenity aggregate HTML report
./gradlew clean test aggregate

# Report location after run:
# build/reports/serenity/index.html
```

### Feature File Location

```
src/test/resources/features/send_kudo.feature
```