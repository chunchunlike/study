package org.xi.quick.test.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class JedisTest {

    public static void main(String[] args) {

        Set<HostAndPort> jedisClusterNode=new HashSet<HostAndPort>();

        jedisClusterNode.add(new HostAndPort("10.11.11.41",6379));
        jedisClusterNode.add(new HostAndPort("10.11.11.42",6379));
        jedisClusterNode.add(new HostAndPort("10.11.11.43",6379));
        jedisClusterNode.add(new HostAndPort("10.11.11.44",6379));
        jedisClusterNode.add(new HostAndPort("10.11.11.45",6379));
        jedisClusterNode.add(new HostAndPort("10.11.11.46",6379));

        JedisCluster cluster = new JedisCluster(jedisClusterNode);

        cluster.set("xi","1");
        System.out.println(cluster.getClusterNodes().size());

    }
}

