# Compose With MVVM

App Android de exemplo que lista usuários da [JSONPlaceholder API](https://jsonplaceholder.typicode.com) com Jetpack Compose, seguindo o padrão funcional do [provider-mvvm-example](../../temp/provider-mvvm-example) e a arquitetura Kotlin do [Resonance](../../Resonance).

## Stack

| Tecnologia | Versão |
|------------|--------|
| Android Gradle Plugin | 9.4.0 |
| Kotlin | 2.2.10 |
| Compose BOM | 2026.02.01 |
| Koin | 4.2.2 |
| Navigation Compose | 2.9.3 |
| Ktor Client | 3.1.3 |
| DataStore | 1.1.7 |
| compileSdk / targetSdk | 37 |
| minSdk | 29 |
| JVM | 21 |

## Arquitetura

MVVM por feature com Koin para injeção de dependências:

```
MainActivity → RoutesApp → UserRoute → UserViewModel → UserRepository → JSONPlaceholder
                ↓                              ↓
           SettingRoute                  ConnectionService + HttpService (Ktor)
                ↓
         SettingViewModel → SettingRepository → DataStore
```

### Estrutura de pacotes

```
src/
├── common/
│   ├── constants/       # ApiConstant, ValueConstant
│   ├── patterns/        # StatePattern, ResultPattern
│   └── services/        # HttpService, ConnectionService
├── di/                  # Módulo Koin
├── design/theme/        # Material 3
├── routes/              # NavHost e rotas
└── features/
    ├── users/           # Lista e detalhe de usuários (API)
    └── settings/        # Tema escuro persistido (DataStore)
```

## Funcionalidades

- Lista de usuários com estados Initial, Loading, Success e Error
- Pull-to-refresh e botão de atualização
- Tela de detalhe com informações pessoais, endereço, contato e empresa
- Configurações com tema escuro persistido via DataStore
- Dialog About com nome, versão e copyright
- Endpoint: `GET https://jsonplaceholder.typicode.com/users`

## Author

William Franco (Dev mobile).

## License

MIT License

Copyright (c) 2026 William Franco

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
