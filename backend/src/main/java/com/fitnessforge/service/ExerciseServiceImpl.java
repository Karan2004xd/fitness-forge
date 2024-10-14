package com.fitnessforge.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fitnessforge.entity.Exercise;
import com.fitnessforge.repository.ExerciseRepository;
import com.fitnessforge.utils.Constants;
import com.fitnessforge.utils.FetchEntityUtil;
import com.fitnessforge.utils.SpecificationUtils;

/** 
 * <b>Description:</b>
 * <p>
 *  Concrete Implementation class for interface {@link com.fitnessforge.service.ExerciseService}
 * </p>
 * */
@Service
public class ExerciseServiceImpl implements ExerciseService {

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private RestTemplate restTemplate;

  /** 
   * Empty default Constructor
   * */
  public ExerciseServiceImpl() {}

  /** 
   * This method makes request to the 3rd party api for
   * the exercises dataset and populates the database only if 
   * sufficient number of exercises are not present in the database.
   *
   * It determines that by comparing the constant REQUIRED_EXERCISES_COUNT
   * (defined in {@link com.fitnessforge.utils.Constants} )
   *
   * */
  @Override
  public void populateDataIfNeeded() {
    Long count = exerciseRepository.getDataCount();

    if (count < Constants.REQUIRED_EXERCISES_COUNT) {
      Exercise[] exercises = restTemplate.getForObject(Constants.EXERCISE_DB_URL_ENDPOINT, Exercise[].class);

      exerciseRepository.saveAll(List.of(exercises));
    }
  }

  /** 
   * This method uses pagination to fetch the fetch the data,
   * in page number and size format.
   *
   * The pagination is done using interfaces 
   * org.springframework.data.domain.PageRequest,
   * org.springframework.data.domain.PageRequest
   *
   * @param page The page number of data to fetch from.
   * @param size The size of total entries to fetch.
   * @return an List {@link com.fitnessforge.entity.Exercise}
   * */
  @Override
  public List<Exercise> getExerciseByPage(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return exerciseRepository.findAll(pageable).getContent();
  }

  /** 
   * This method uses pagination to fetch the fetch the data,
   * in page number and size format.
   *
   * @return total count of the exercise in the database.
   * */
  @Override
  public Long getTotalExercisesCount() {
    return exerciseRepository.getDataCount();
  }

  /** 
   * Fetches the {@link com.fitnessforge.entity.Exercise} by its numeric id.
   *
   * @param exerciseId the numeric id of entity {@link com.fitnessforge.entity.Exercise} 
   * @return an object of entity {@link com.fitnessforge.entity.Exercise}
   * */
  @Override
  public Exercise getExerciseById(Long exerciseId) {
    return FetchEntityUtil.GetEntity(exerciseRepository.findByExerciseId(exerciseId), Exercise.class);
  }

  @Override
  public List<Exercise> getExerciseByPage(int page, int size, Map<String, List<String>> filters) {
    Pageable pageable = PageRequest.of(page, size);
    Specification<Exercise> spec = Specification.where(null);

    for (String filter : filters.keySet()) {
      List<String> value = filters.get(filter);

      if (value != null) {
        if (filter == "name") {
          spec = spec.and(SpecificationUtils.like(filter, value.get(0)));
        } else {
          spec = spec.and(SpecificationUtils.in(filter, value));
        }
      }
    }

    return exerciseRepository.findAll(spec, pageable).getContent();
  }
}
