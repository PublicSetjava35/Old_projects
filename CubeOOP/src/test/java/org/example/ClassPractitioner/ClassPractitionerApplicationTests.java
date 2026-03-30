package org.example.ClassPractitioner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;


@ExtendWith(MockitoExtension.class)
class ClassPractitionerApplicationTests {

     @Test
     void TestKafka() {

     }
     public Stream<Arguments> voids() {
          return Stream.of(Arguments.of());
     }
}
