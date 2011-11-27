package de.bitnoise.sonferenz.service.v2.extend;


import de.bitnoise.sonferenz.model.ConferenceModel;
import de.bitnoise.sonferenz.service.v2.extend.ports.TimeTable;

public interface CalculateTalkOrder
{

  TimeTable calculate(ConferenceModel conference);

}
