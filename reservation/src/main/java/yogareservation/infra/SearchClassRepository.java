package yogareservation.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import yogareservation.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "searchClasses",
    path = "searchClasses"
)
public interface SearchClassRepository
    extends PagingAndSortingRepository<SearchClass, Long> {}
