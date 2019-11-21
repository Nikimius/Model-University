package com.example.demo.vuz.services;

import com.example.demo.vuz.dto.UniversityDto;
import com.example.demo.vuz.model.Faculty;
import com.example.demo.vuz.model.University;
import com.example.demo.vuz.repositories.FacultyRepository;
import com.example.demo.vuz.repositories.UniversityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;

    public UniversityService(UniversityRepository universityRepository, FacultyRepository facultyRepository) {
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
    }

    public void createUniversity(String name, String webSite, String city, List<Integer> facultiesIds){
        University newUniversity = new University();
        newUniversity.setName(name);
        newUniversity.setWebSite(webSite);
        newUniversity.setCity(city);

        List<Faculty> faculties = facultyRepository.findAllById(facultiesIds);
        faculties.forEach(faculty -> faculty.setUniversity(newUniversity));
        newUniversity.setFacultyList(faculties);

        universityRepository.save(newUniversity);
    }

    public void removeUniver(List<Integer> universitiesIds){
        List<University> universities = universityRepository.findAllById(universitiesIds);
        universities.forEach(university -> delUn(university));
    }

    public void delUn(University university){
        List<Faculty> faculties = university.getFacultyList();
        faculties.forEach(faculty -> faculty.setUniversity(null));
        universityRepository.delete(university);
    }


    public void createUniversity(UniversityDto universityDto) {
        University newUniversity = new University();
        newUniversity.setName(universityDto.getName());
        newUniversity.setWebSite(universityDto.getWebSite());
        newUniversity.setCity(universityDto.getCity());
    }
}
