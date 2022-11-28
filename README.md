# CZERTAINLY-Utils-Service

> This repository is part of the commercial open-source project CZERTAINLY. You can find more information about the project at [CZERTAINLY](https://github.com/3KeyCompany/CZERTAINLY) repository, including the contribution guide.

This Utils Service contains support function for platform components, for example:
- consistent parsing certificate information, including certification request
- consistent decoding and encoding certificates
- information about object identifiers

## Exposed endpoints

| Endpoint                                                                                                | Short description                                                                                      |
|---------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| [CertificateUtilsController](src/main/java/com/czertainly/utils/api/v1/CertificateUtilsController.java) | Certificate handling support tools that contains for example parsing of certificate in various formats |
| [OidUtilsController](src/main/java/com/czertainly/utils/api/v1/OidUtilsController.java)                 | Details about object identifiers like friendly name                                                    |

## Docker container

Utils Service is provided as a Docker container. Use the `docker pull 3keycompany/czertainly-utils-service:tagname` to pull the image from the repository. It can be configured using the following environment variables:

| Variable    | Description                                           | Required                                        | Default value |
|-------------|-------------------------------------------------------|-------------------------------------------------|---------------|
| `PORT`      | Port where the service is exposed                     | ![NO](https://img.shields.io/badge/-NO-red.svg) | `8080`        |
| `LOG_LEVEL` | Logging level, can be one of `INFO`, `DEBUG`, `TRACE` | ![NO](https://img.shields.io/badge/-NO-red.svg) | `INFO`        |