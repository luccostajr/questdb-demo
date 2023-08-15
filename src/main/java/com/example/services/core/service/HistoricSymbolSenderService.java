package com.example.services.core.service;

import com.example.domain.core.Entity;
import com.example.domain.entities.data.DoubleHistoricData;
import com.example.services.questdb.service.HistoricSymbolQuestDBService;
import com.example.services.questdb.service.QuestDBService;

// public class HistoricSymbolSenderService extends SenderService<DefaultHistoricData<UUID>> {
//   // HistoricData extends DefaultHistoricData<UUID>
//   // CTOR: SenderService(Entity<T> entity) ==> SenderService(Entity<HistoricData> entity) ==> SenderService(Entity<DefaultHistoricData<UUID>> entity)
//   // Historic extends AbstractHistoric<UUID>
//   // abstract class AbstractHistoric<T> extends Entity<DefaultHistoricData<T>>
//   // Historic extends AbstractHistoric<UUID> ==> Historic extends Entity<DefaultHistoricData<UUID>>
//   public HistoricSymbolSenderService(Historic historic) { 
//     super(historic);
//   } 

//   public void execute() {
//     HistoricSymbolQuestDBService questDBService = (HistoricSymbolQuestDBService) getQuestDBService(); 
    
//     questDBService.processHistoricList(getEntity());
//     questDBService.flushAndClose();
//   }

//   @Override
//   protected QuestDBService createQuestDBService() {
//     return new HistoricSymbolQuestDBService(); 
//   }
// }

public class HistoricSymbolSenderService<T> extends SenderService<DoubleHistoricData<T>> {
  public HistoricSymbolSenderService(Entity<DoubleHistoricData<T>> entity) { 
    super(entity);
  } 

  public void execute() {
    HistoricSymbolQuestDBService<T> questDBService = (HistoricSymbolQuestDBService<T>) getQuestDBService(); 

    Entity<DoubleHistoricData<T>> entity = getEntity();
    
    try {
      questDBService.processEntity(entity);
    } 
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    questDBService.flushAndClose();
  }

  @Override
  protected QuestDBService createQuestDBService() {
    return new HistoricSymbolQuestDBService<T>(); 
  }
}
