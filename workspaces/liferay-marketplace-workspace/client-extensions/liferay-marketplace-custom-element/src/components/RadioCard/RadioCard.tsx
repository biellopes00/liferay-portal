import {ClayToggle} from '@clayui/form';
import classNames from 'classnames';

import radioChecked from '../../assets/icons/radio-button-checked.svg';
import radioUnchecked from '../../assets/icons/radio-button-unchecked.svg';

import './RadioCard.scss';
import {Tooltip} from '../Tooltip/Tooltip';

interface RadioCardProps {
	description: string;
	disabled?: boolean;
	icon?: string;
	onChange: (value?: boolean) => void;
	selected: boolean;
	title: string;
	toggle?: boolean;
	tooltip: string;
}

export function RadioCard({
	description,
	disabled = false,
	icon,
	onChange,
	selected,
	title,
	toggle = false,
	tooltip,
}: RadioCardProps) {
	return (
		<div
			className={classNames('radio-card-container', {
				'radio-card-container-disabled': disabled,
				'radio-card-container-selected': selected,
			})}
		>
			<div className="radio-card-main-info">
				<div className="radio-card-title">
					{toggle ? (
						<ClayToggle
							onToggle={(toggleValue) => onChange(toggleValue)}
							toggled={selected}
						/>
					) : (
						<button
							className={classNames('radio-card-button', {
								'radio-card-button-disabled': disabled,
							})}
							onClick={() => !disabled && onChange()}
						>
							<img
								alt={
									selected
										? 'Radio Checked'
										: 'Radio unchecked'
								}
								className="radio-card-button-icon"
								src={selected ? radioChecked : radioUnchecked}
							/>
						</button>
					)}

					<span
						className={classNames('radio-card-title-text', {
							'radio-card-title-text-selected': selected,
						})}
					>
						{title}
					</span>

					<img
						alt="Icon"
						className={classNames('radio-card-title-icon', {
							'radio-card-title-icon-selected': selected,
						})}
						src={icon}
					/>
				</div>

				<div className="radio-card-title-tooltip">
					<Tooltip tooltip={tooltip} />
				</div>
			</div>

			<span className="radio-card-description">{description}</span>
		</div>
	);
}
