- [x] Example3 (& general): do we need multiple state machine states for each object created from that class if they run in parallel or must be synchronized?

  - yes, we need multiple states (basically one for each thread)

- [x] Example4 (& general): is it better to include the new thread, start() and run()?

  - no, it's not necessary; donno about better, maybe it will be too confusing

- [x] Example4 (sequence diagram): how can I implement the try-catch block?

  - check Obsidian: /Java/UML/Sequence Diagrams for an implementation of such a block

- [ ] Example4 (sequence diagram): Can we not use `par` combined fragment over all 3 threads in order to show parallelism?
