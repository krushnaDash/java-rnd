package com.krushna.Java_rnd.neetcode.backtracking;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class CombinationSumTest {
    @Test
    public void testCombinationSumLoop_basic() {
        CombinationSum cs = new CombinationSum();
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(2,2,3),
            Arrays.asList(7)
        );
        List<List<Integer>> result = cs.combinationSumLoop(candidates, target);
        assertTrue(containsSameCombinations(expected, result));
    }

    @Test
    public void testCombinationSumLoop_singleCandidate() {
        CombinationSum cs = new CombinationSum();
        int[] candidates = {2};
        int target = 4;
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(2,2)
        );
        List<List<Integer>> result = cs.combinationSumLoop(candidates, target);
        assertTrue(containsSameCombinations(expected, result));
    }

    @Test
    public void testCombinationSumLoop_noSolution() {
        CombinationSum cs = new CombinationSum();
        int[] candidates = {2,4,6};
        int target = 5;
        List<List<Integer>> expected = Collections.emptyList();
        List<List<Integer>> result = cs.combinationSumLoop(candidates, target);
        assertTrue(result.isEmpty());
    }

    private boolean containsSameCombinations(List<List<Integer>> expected, List<List<Integer>> actual) {
        Set<List<Integer>> set1 = new HashSet<>();
        for (List<Integer> l : expected) {
            List<Integer> copy = new ArrayList<>(l);
            Collections.sort(copy);
            set1.add(copy);
        }
        Set<List<Integer>> set2 = new HashSet<>();
        for (List<Integer> l : actual) {
            List<Integer> copy = new ArrayList<>(l);
            Collections.sort(copy);
            set2.add(copy);
        }
        return set1.equals(set2);
    }
}
