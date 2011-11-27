package de.bitnoise.sonferenz.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.model.TalkModel;

public interface TalkRepository extends JpaRepository<TalkModel, Integer>
{

  List<TalkModel> findAllByConference(ConferenceModel conference);

  @Query("select t from TalkModel t where t.conference is not null")
  Page<TalkModel> test(Pageable  request);

  @Query("select count(t) from TalkModel t where t.conference is not null")
  Long countAllVotable();
}
