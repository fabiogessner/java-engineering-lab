# Java paradigms and OOP

Build three implementations of the same Pricing Rules Engine: imperative, OOP, functional.

Current milestone defines behavior and immutable inputs. No pricing implementation is available. Read [problem.md](docs/problem.md) before adding an engine. The disabled acceptance tests are a backlog, while invariant tests validate the input model.

Run from repository root: `mvn -pl modules/01-java-paradigms -am test`.

Next: define the PricingEngine contract and add its imperative implementation, enable `PricingAcceptanceTest`, and satisfy every case without weakening assertions. Later implementations must reuse the same acceptance suite.
