package com.zcp.part5.c251to300;

/**
 * @author ZCP
 * @title: C262
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/trips-and-users/submissions/
 * @date 2022/9/23 9:17
 */
public class C262 {

    /**
     *     select
     *     t.request_at "Day",
     *     ifnull(round(t2.cnt / count(*),2),0.00) "Cancellation Rate"
     *     from Trips t
     *     left join Users cu on t.client_id = cu.users_id
     *     left join Users du on t.driver_id = du.users_id
     *
     *     left join (
     *         select
     *         t.request_at,
     *         count(*) cnt
     *         from Trips t
     *         left join Users cu on t.client_id = cu.users_id
     *         left join Users du on t.driver_id = du.users_id
     *         where cu.banned = 'No' and du.banned = 'No'
     *         and (t.status = 'cancelled_by_driver' or  t.status = 'cancelled_by_client')
     *         and t.request_at >= '2013-10-01' and t.request_at <= '2013-10-03'
     *         group by  t.request_at
     *     ) t2 on t2.request_at = t.request_at
     *     where cu.banned = 'No' and du.banned = 'No'
     *     and t.request_at >= '2013-10-01' and t.request_at <= '2013-10-03'
     *     group by  t.request_at
     */
}
