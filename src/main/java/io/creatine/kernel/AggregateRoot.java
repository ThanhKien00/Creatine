package io.creatine.kernel;

import org.springframework.data.domain.AbstractAggregateRoot;

@org.jmolecules.ddd.annotation.AggregateRoot
public abstract class AggregateRoot<A extends AbstractAggregateRoot<A>> extends AbstractAggregateRoot<A> {}
