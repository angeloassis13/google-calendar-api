# Google Calendar integration API

## Overview

This README provides an overview of a basic API designed for a make up artist to calculate invoicing.

It serves as a guide to understand the project structure, how to set up the development environment, run the application, and customize it to suit your needs.

The basic use case is that every appointment is saved at Google Calendar.
The API has a configuration with the price since a specific date.

For example, considering the following configuration:

- 2023-01-01: 100
- 2023-02-01: 120
- 2023-02-15: 150

Each appointment in the interval from 2023-01-01 to 2023-02-01 will cost 100.

From 2023-02-01 to 2023-02-15, each appointment will cost 120.

Finally, from 2023-02-15, each appointment will cost 150.

## Prerequisites

Before you begin, ensure you have the following tools and software installed:

- Java Development Kit (JDK) 17 or higher
- Apache Maven
- Your favorite Integrated Development Environment (IDE), such as Eclipse or IntelliJ IDEA **with lombok plugin installed**

### Google credentials

Since the API needs to call Google Calendar API, a google credential is needed.
The system expect a file called `credentials.json` in `resources` folder.

This file can be obtained at [Google for Developers](https://developers.google.com/)

## Getting Started

Follow these steps to get the Spring Boot application up and running:

1. Clone or download this repository to your local machine.
1. Open the project in your preferred IDE.
1. Build the project using Maven. You can do this via the command line:

```bash
mvn clean install
```

### Running the application

Run the application by executing the main class SpringBootBasicApplication. You can typically do this by right-clicking the class and selecting "Run" in your IDE.

Once the application is running, open a web browser and navigate to http://localhost:9000/swagger-ui/index.html. You should see a page with all endpoints available.

## Frontend

A React application was built for this API. It's available at [Calendar App](https://github.com/angeloassis13/calendar-app)

## Next steps

This API is already functional and usefull, but it would be better with some improvements, like:

- Add unit tests
- Improve exception handling
- Add configuration based on environment variables
