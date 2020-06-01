package ua.test.hotel.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.test.hotel.model.Application;
import ua.test.hotel.model.Room;
import ua.test.hotel.model.RoomState;
import ua.test.hotel.model.User;
import ua.test.hotel.model.dto.ApplicationDTO;
import ua.test.hotel.repo.ApplicationRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final RoomService roomService;
    private final ApplicationRepo applicationRepo;

    public ApplicationService(RoomService roomService, ApplicationRepo applicationRepo) {
        this.roomService = roomService;
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
                .approved(false)
                .build();

        applicationRepo.save(application);

    }

    public List<Application> showApprove() {
        return applicationRepo.findAllByApprovedIsTrue();
    }

    @Transactional
    public void approveApplication(Long id) {
        Application application = findById(id).get();
        application.setApproved(true);
        saveApplication(application);
    }

    @Transactional
    public void deleteApplicationAndFreeRoom(Long id) {
        findById(id).get().getRoom().setRoomState(RoomState.AVAILABLE);
        roomService.save(findById(id).get().getRoom());
        deleteById(id);
    }
}
