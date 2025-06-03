# Modulith Architecture

## Status

Author: Thanh Kien

## Context

Creatine Platform is a comprehensive fitness-nutrition system to provide personalized meal planning, workout routines,
progress tracking, and community engagement. So the system must balance modularity, scalability, and maintainability.
Team development is small-sized with limited DevOps resources for managing distributed systems.

## Decision

We'll adopt **Modulith Architecture** by using Spring Modulith for the project. A modulith stands for modular monolithic
architecture that structures the application as a collection of loosely coupled modules with well-defined boundaries,
while maintaining deployment as a single unit.

## Proposal

### Domain-driven Design

Creatine naturally decomposes into bounded contexts: Account Management, Meal Management, Training Routine, Calories
Analysis, and Dietary Recommendation. Each of these domains has distinct data models, business rules and lifecycle
management requirements. Modulith Architecture enforces clear module boundaries while allowing shared data models where
appropriate.

### Inner-module Communication

Each domain module can be developed independently without breaking other modules. Spring Modulith provides mechanisms
for modules to communicate with each other through events or exposed interfaces, maintaining loose coupling. It also
could be done using event between modules.

### Simplified Testing and Development

One of the implementations of Modulith Architecture, Spring Modulith, supports testing at module level, enabling
developers to write and run tests for individual modules without the need for the entire application context.

## Consequences

### Advantages

- Faster development cycles with simplified debugging and testing.
- Strong data consistency for nutritional calculation.
- Clear module boundaries improve code organization.
- Future migration path to microservices if needed.

### Disadvantages

- Single point of failure for the entire system.
- Technology stacks constraints apply to all modules.
- Database schema changes may require coordination across modules.

## Appendix A: Spring Modulith
