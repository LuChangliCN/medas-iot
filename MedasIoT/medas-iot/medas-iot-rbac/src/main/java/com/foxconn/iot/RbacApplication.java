package com.foxconn.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.foxconn.iot.support.Snowflaker;
import com.mysql.cj.util.StringUtils;

@SpringBootApplication
@EnableJpaAuditing
public class RbacApplication {

	/** -jar 传入参数依次是： workerId, datacenterId */
	public static void main(String[] args) {
		SpringApplication.run(RbacApplication.class, args);

		if (args.length > 1) {
			if (StringUtils.isStrictlyNumeric(args[0]) && StringUtils.isStrictlyNumeric(args[1])) {
				int workerId = Integer.parseInt(args[0]);
				int datacenterId = Integer.parseInt(args[1]);
				Snowflaker.init(workerId, datacenterId);
			}
		}
	}
}
