package io.creatine.sharedkernel;

/**
 * Domain Event indicates that something occurred within the domain that other parts of the same domain to be aware of.
 * Domain Events are typically immutable and carry information about the occurrence in the domain
 * <p>
 * Domain Events are one specific type of Event, which are typically immutable and carry information, used for notifying
 * local or remote Bounded Context of Domain state changes. Examples of Domain Events are "AccountRegistered",
 * "ProductAdded", etc.
 *
 * @author ThanhKien00
 */
public interface DomainEvent {
}
