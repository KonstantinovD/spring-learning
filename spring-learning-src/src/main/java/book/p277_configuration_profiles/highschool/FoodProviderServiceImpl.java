package book.p277_configuration_profiles.highschool;

import book.p277_configuration_profiles.Food;
import book.p277_configuration_profiles.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {
  @Override
  public List<Food> provideLunchSet() {
    List<Food> lunchSet = new ArrayList<>();
    lunchSet.add(new Food ( "Coke"));
    lunchSet.add(new Food("Hamburger"));
    lunchSet.add(new Food("French Fries"));
    return lunchSet;
  }
}
