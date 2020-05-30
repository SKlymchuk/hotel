package ua.test.hotel.services;

import org.springframework.stereotype.Service;
import ua.test.hotel.model.Application;
import ua.test.hotel.model.Room;
import ua.test.hotel.model.User;
import ua.test.hotel.model.dto.ApplicationDTO;
import ua.test.hotel.repo.ApplicationRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepo applicationRepo;

    public ApplicationService(ApplicationRepo applicationRepo) {
        this.applicationRepo = applicationRepo;
    }

    public List<Application> findAll() {
        return applicationRepo.findAll();
    }

    public void saveApplication(Application application) {
        applicationRepo.save(application);
    }

    public List<Application> findAllByUser(User user) {
        return applicationRepo.findAllByUser(user);
    }

    public Optional<Application> findById(Long id) {
        return applicationRepo.findById(id);
    }

    public void deleteById(Long id) {
        applicationRepo.deleteById(id);
    }

    public void saveApplicationFromDTO(ApplicationDTO applicationDTO, Room room, User user) {
        Application application = Application.builder()
                .startDate(LocalDate.parse(applicationDTO.getStartDate()))
                .finishDate(LocalDate.parse(applicationDTO.getFinishDate()))
                .room(room)
                .user(user)
                .build();

        applicationRepo.save(application);

    }
}
