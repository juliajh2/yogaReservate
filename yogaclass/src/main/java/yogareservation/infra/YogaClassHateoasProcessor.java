package yogareservation.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import yogareservation.domain.*;

@Component
public class YogaClassHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<YogaClass>> {

    @Override
    public EntityModel<YogaClass> process(EntityModel<YogaClass> model) {
        return model;
    }
}
