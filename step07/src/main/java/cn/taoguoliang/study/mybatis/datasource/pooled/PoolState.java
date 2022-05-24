package cn.taoguoliang.study.mybatis.datasource.pooled;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     池化状态
 * </p>
 *
 * @author taogl
 * @since 2022/4/26 20:33
 **/
public class PoolState {

  protected PooledDataSource dataSource;

  protected final List<PooledConnection> idleConnections = new ArrayList<>();
  protected final List<PooledConnection> activeConnections = new ArrayList<>();
  protected long requestCount = 0;
  // 累计请求时间
  protected long accumulatedRequestTime = 0;
  // 累计创建时间
  protected long accumulatedCheckoutTime = 0;
  // 过期连接数
  protected long claimedOverdueConnectionCount = 0;
  // 过期连接累计创建时间
  protected long accumulatedCheckoutTimeOfOverdueConnections = 0;
  protected long accumulatedWaitTime = 0;
  protected long hadToWaitCount = 0;
  protected long badConnectionCount = 0;

  public PoolState(PooledDataSource dataSource) {
    this.dataSource = dataSource;
  }

  public synchronized long getRequestCount() {
    return requestCount;
  }

  public synchronized long getAverageRequestTime() {
    return requestCount == 0 ? 0 : accumulatedRequestTime / requestCount;
  }

  public synchronized long getAverageWaitTime() {
    return hadToWaitCount == 0 ? 0 : accumulatedWaitTime / hadToWaitCount;

  }

  public synchronized long getHadToWaitCount() {
    return hadToWaitCount;
  }

  public synchronized long getBadConnectionCount() {
    return badConnectionCount;
  }

  public synchronized long getClaimedOverdueConnectionCount() {
    return claimedOverdueConnectionCount;
  }

  public synchronized long getAverageOverdueCheckoutTime() {
    return claimedOverdueConnectionCount == 0 ? 0 : accumulatedCheckoutTimeOfOverdueConnections / claimedOverdueConnectionCount;
  }

  public synchronized long getAverageCheckoutTime() {
    return requestCount == 0 ? 0 : accumulatedCheckoutTime / requestCount;
  }


  public synchronized int getIdleConnectionCount() {
    return idleConnections.size();
  }

  public synchronized int getActiveConnectionCount() {
    return activeConnections.size();
  }

  @Override
  public synchronized String toString() {
    return "PoolState{" +
            "dataSource=" + dataSource +
            ", idleConnections=" + idleConnections +
            ", activeConnections=" + activeConnections +
            ", requestCount=" + requestCount +
            ", accumulatedRequestTime=" + accumulatedRequestTime +
            ", accumulatedCheckoutTime=" + accumulatedCheckoutTime +
            ", claimedOverdueConnectionCount=" + claimedOverdueConnectionCount +
            ", accumulatedCheckoutTimeOfOverdueConnections=" + accumulatedCheckoutTimeOfOverdueConnections +
            ", accumulatedWaitTime=" + accumulatedWaitTime +
            ", hadToWaitCount=" + hadToWaitCount +
            ", badConnectionCount=" + badConnectionCount +
            '}';
  }
}