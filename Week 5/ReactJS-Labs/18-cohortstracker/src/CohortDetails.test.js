import React from 'react';
import { render, screen } from '@testing-library/react';
import CohortDetails from './CohortDetails';
import { CohortData } from './Cohort';

describe("Cohort Details Component", () => {
  const sampleCohort = CohortData[0];

  test("should create the component", () => {
    const { container } = render(<CohortDetails cohort={sampleCohort} />);
    expect(container).toBeInTheDocument();
  });

  test("should initialize the props", () => {
    render(<CohortDetails cohort={sampleCohort} />);
    const headingElement = screen.getByRole('heading', { level: 3 });
    expect(headingElement).toBeInTheDocument();
  });

  test("should display cohort code in h3", () => {
    render(<CohortDetails cohort={sampleCohort} />);
    const headingElement = screen.getByRole('heading', { level: 3 });
    expect(headingElement.textContent).toContain(sampleCohort.cohortCode);
  });

  test("should always render same html", () => {
    const { container } = render(<CohortDetails cohort={sampleCohort} />);
    expect(container.firstChild).toMatchSnapshot();
  });
});