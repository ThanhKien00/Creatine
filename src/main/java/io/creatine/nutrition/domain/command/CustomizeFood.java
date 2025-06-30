package io.creatine.nutrition.domain.command;

import io.creatine.nutrition.domain.valueobject.Instruction;
import io.creatine.nutrition.domain.valueobject.MacroNutrient;
import io.creatine.sharedkernel.Command;

import java.util.List;

public record CustomizeFood(
        String title,
        String description,
        String imageUrl,
        Integer calories,
        Boolean certified,
        List<String> tags,
        MacroNutrient macro,
        List<Instruction> instructions,
        String menuId) implements Command {
}
