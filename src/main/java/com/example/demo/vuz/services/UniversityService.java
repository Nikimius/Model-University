package com.example.demo.vuz.services;

import com.example.demo.vuz.model.Faculty;
import com.example.demo.vuz.model.University;
import com.example.demo.vuz.repositories.FacultyRepository;
import com.example.demo.vuz.repositories.UniversityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;

    public UniversityService(UniversityRepository universityRepository, FacultyRepository facultyRepository) {
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
    }

    public University createUniversityDto(String name, String webSite, String city, List<Integer> facultiesIds) {
        University newUniversity = new University();
        newUniversity.setName(name);
        newUniversity.setWebSite(webSite);
        newUniversity.setCity(city);

        List<Faculty> faculties = facultyRepository.findAllById(facultiesIds);
        faculties.forEach(faculty -> faculty.setUniversity(newUniversity));
        newUniversity.setFacultyList(faculties);

        return universityRepository.save(newUniversity);
    }

    public University updateUniversityById(int universityId, Map<String, Object> dto) {
        University university = universityRepository.findById(universityId).orElseThrow(()-> new IllegalArgumentException("University not found"));

        if (dto.containsKey("name")) {
            university.setName((String) dto.get("name"));
        }

        if (dto.containsKey("webSite")) {
            university.setWebSite((String) dto.get("webSite"));
        }

        if (dto.containsKey("city")) {
            university.setCity((String) dto.get("city"));
        }

        if (dto.containsKey("facultiesIds")) {
            @SuppressWarnings("unchecked")
            List<Integer> facultiesIds = (List<Integer>) dto.get("facultiesIds");
            List<Faculty> faculties = facultyRepository.findAllById(facultiesIds);
            faculties.forEach(faculty -> {
                faculty.setUniversity(university);
                facultyRepository.save(faculty);
            });
        }
        return universityRepository.save(university);
    }

    public void deleteUniversitiesByIdIn(List<Integer> universitiesIds) {
        List<University> universities = universityRepository.findAllById(universitiesIds);
        universities.forEach(universityRepository::delete);
    }

    /*public void createUniversityDto(UniversityDto universityDto) {
        University newUniversity = new University();
        newUniversity.setName(universityDto.getName());
        newUniversity.setWebSite(universityDto.getWebSite());
        newUniversity.setCity(universityDto.getCity());
    }*/
}
