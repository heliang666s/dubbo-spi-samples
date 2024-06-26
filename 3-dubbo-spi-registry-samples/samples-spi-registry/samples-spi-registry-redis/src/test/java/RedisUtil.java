/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import redis.embedded.RedisServer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static redis.embedded.RedisServer.newRedisServer;

public class RedisUtil {

    private  static RedisServer redisServer;

    public static void setUp() throws Exception {
        int redisPort = 6379;
        try {
            redisServer = newRedisServer()
                    .port(redisPort)
                    // set maxheap to fix Windows error 0x70 while starting redis
//                    .settingIf(SystemUtils.IS_OS_WINDOWS, "maxheap 128mb")
                    .build();
            redisServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void tearDown() throws Exception {
        redisServer.stop();
    }

    public static void main(String[] args) throws Exception {
        setUp();
        System.out.println("embedded-redis started");
        new CountDownLatch(1).await();
    }
}
