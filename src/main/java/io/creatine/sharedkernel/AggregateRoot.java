package io.creatine.sharedkernel;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AggregateRoot<A extends AggregateRoot<A>> {

    @Transient
    private transient final List<Object> domainEvents = new CopyOnWriteArrayList<>();

    protected <T> T dispatchEvent(T event) {
        Assert.notNull(event, "Domain Event cannot be null.");
        this.domainEvents.add(event);
        return event;
    }

    @DomainEvents
    protected Collection<Object> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    @AfterDomainEventPublication
    protected void clearEvents() {
        this.domainEvents.clear();
    }

    @SuppressWarnings("unchecked")
    protected final A andEventsFrom(AggregateRoot<?> otherAggregate) {
        Assert.notNull(otherAggregate, "Aggregate cannot be null.");
        if (!this.equals(otherAggregate)) {
            this.domainEvents.addAll(otherAggregate.domainEvents());
        }
        return (A) this;
    }

    @SuppressWarnings("unchecked")
    protected A andEvent(Object event) {
        dispatchEvent(event);
        return (A) this;
    }

}
