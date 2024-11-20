package yogareservation.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yogareservation.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "yogaClasses",
    path = "yogaClasses"
)
public interface YogaClassRepository
    extends PagingAndSortingRepository<YogaClass, Long> {}
