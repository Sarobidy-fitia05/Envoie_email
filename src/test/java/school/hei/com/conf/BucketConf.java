package school.hei.com.conf;

import org.springframework.test.context.DynamicPropertyRegistry;
import school.hei.com.PojaGenerated;

@PojaGenerated
public class BucketConf {

  void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("aws.s3.bucket", () -> "dummy-bucket");
  }
}
