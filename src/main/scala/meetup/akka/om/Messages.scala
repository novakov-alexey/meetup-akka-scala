package meetup.akka.om

import java.math.BigDecimal
import java.time.{LocalDate, LocalDateTime}

import meetup.akka.om.OrderType.OrderType

case class Order(orderId: Long = -1, executionDate: LocalDateTime, orderType: OrderType, executionPrice: BigDecimal,
                 symbol: String, userId: Int, quantity: Int) {
  def this(orderType: OrderType, executionPrice: BigDecimal, symbol: String, userId: Int, quantity: Int) =
    this(-1, LocalDateTime.now, orderType, executionPrice, symbol, userId, quantity)

  def this(orderId: Long, order: Order) =
    this(orderId, order.executionDate, order.orderType, order.executionPrice, order.symbol, order.userId, order.quantity)
}

case class Execution(orderId: Long, quantity: Int, executionDate: LocalDateTime)

case class NewOrder(order: Order)

case class PreparedOrder(order: Order, orderId: Long)

case class LoggedOrder(deliveryId: Long, order: Order)

case class LogOrder(deliveryId: Long, preparedOrder: PreparedOrder)

case class ExecuteOrder(orderId: Long, quantity: Int)

case class ExecutedQuantity(orderId: Long, quantity: Int, executionDate: LocalDateTime)

case class ExecutionAck(orderId: Long, quantity: Int, executionId: Long)

case class AllAcksReceived(replies: Seq[ExecutionAck])

case class CompleteBatch(upToId: Int, withDate: LocalDateTime)

case class BatchCompleted(upToId: Int)