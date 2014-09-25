package com.tinkerpop.gremlin.process.graph.step.map;

import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.graph.marker.EngineDependent;
import com.tinkerpop.gremlin.process.graph.marker.PathConsumer;
import com.tinkerpop.gremlin.process.util.TraversalHelper;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class BackStep<S, E> extends MapStep<S, E> implements PathConsumer, EngineDependent {

    public String stepLabel;
    private boolean requiresPaths = false;

    public BackStep(final Traversal traversal, final String stepLabel) {
        super(traversal);
        this.stepLabel = stepLabel;
        TraversalHelper.getStep(this.stepLabel, this.traversal);
        this.setFunction(traverser -> this.requiresPaths() ? traverser.getPath().get(this.stepLabel) : traverser.get(this.stepLabel));
    }

    @Override
    public boolean requiresPaths() {
        return this.requiresPaths;
    }

    @Override
    public void onEngine(final Engine engine) {
        this.requiresPaths = engine.equals(Engine.COMPUTER);
    }

    @Override
    public String toString() {
        return TraversalHelper.makeStepString(this, this.stepLabel);
    }

    @Override
    public BackStep clone() throws CloneNotSupportedException {
        final BackStep<S, E> clone = (BackStep) super.clone();
        clone.setFunction(traverser -> clone.requiresPaths() ? traverser.getPath().get(clone.stepLabel) : traverser.get(clone.stepLabel));
        return clone;
    }
}
